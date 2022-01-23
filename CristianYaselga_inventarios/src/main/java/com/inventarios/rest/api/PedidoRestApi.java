package com.inventarios.rest.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.inventarios.dto.InformacionMontoVendidoRequestDto;
import com.inventarios.dto.InformacionTransaccionesRequestDto;
import com.inventarios.dto.PedidoDetalleRequestDto;
import com.inventarios.dto.PedidoRequestDto;
import com.inventarios.dto.StockMockDto;
import com.inventarios.jpa.entity.Cliente;
import com.inventarios.jpa.entity.Pedido;
import com.inventarios.jpa.entity.Producto;
import com.inventarios.jpa.entity.TiendaProducto;
import com.inventarios.services.ClienteService;
import com.inventarios.services.PedidoService;
import com.inventarios.services.ProductoService;
import com.inventarios.services.TiendaProductoService;
import com.inventarios.util.Duplex;

/**
 * Rest controller for ADD, PUT, DELETE, GET, LOGIN of a Cliente
 * 
 * @author CRIS
 *
 */
@RestController
public class PedidoRestApi {

	@Autowired
	ClienteService clienteService;

	@Autowired
	TiendaProductoService tiendaProdcutoService;

	@Autowired
	PedidoService pedidoService;

	@Autowired
	ProductoService productoService;

	private static final String URL_STOCK_FIVE = "https://mocki.io/v1/fbb2a178-839c-4390-9cee-765762823c29";

	private static final String URL_STOCK_TEN = "https://mocki.io/v1/ec1226d5-3dfe-46c0-a109-24dba7361bc6";

	/**
	 * Endpoint for create Pedido, need token for use
	 * 
	 * @return
	 */
	@RequestMapping(value = "/pedido", method = RequestMethod.POST)
	public ResponseEntity<String> realizarPedido(@RequestBody PedidoRequestDto request) {
		if (request == null || request.getIdentificacionCliente() == null || request.getDetalle() == null
				|| request.getIdentificacionCliente().isEmpty() || request.getDetalle().isEmpty()) {
			return ResponseEntity.badRequest()
					.body("Datos Incompletos: Para realizar un pedido debe ingresar: identificacionCliente y detalle");

		}
		List<Cliente> clientes = clienteService.buscarClientePorIdentificacion(request.getIdentificacionCliente());
		if (clientes == null || clientes.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("El cliente con identificacion "
					+ request.getIdentificacionCliente() + " no se encuentra registrado ");

		}
		Cliente cliente = clientes.get(0);

		// Map for group all pedidos of producto
		Map<String, Duplex<Producto, Integer>> mapaProductoCantidad = new HashMap<>();

		List<Pedido> pedidos = new ArrayList<>();
		for (PedidoDetalleRequestDto detalle : request.getDetalle()) {
			TiendaProducto registro = tiendaProdcutoService.buscarRegistroPorCodigoTiendaProducto(detalle.getCodigoTienda(),
					detalle.getCodigoProducto());
			if (registro == null) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("El producto con codigo " + detalle.getCodigoProducto()
								+ " no ha sido asignado a la tienda " + detalle.getCodigoTienda());

			}
			if (mapaProductoCantidad.containsKey(detalle.getCodigoProducto())) {
				Duplex<Producto, Integer> record = mapaProductoCantidad.get(detalle.getCodigoProducto());
				record.setSecondElement(record.getSecondElement() + detalle.getCantidad());
				if (validarDiferenciaMayorDisponible(registro.getIdProducto(), record.getSecondElement())) {
					// reject transacction
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
							.body("Unidades no disponibles >10 del producto con codigo " + detalle.getCodigoProducto());
				}
				mapaProductoCantidad.put(detalle.getCodigoProducto(), record);
			} else {
				if (validarDiferenciaMayorDisponible(registro.getIdProducto(), detalle.getCantidad())) {
					// reject transacction
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
							.body("Unidades no disponibles >10 del producto con codigo " + detalle.getCodigoProducto());
				}
				mapaProductoCantidad.put(detalle.getCodigoProducto(),
						new Duplex<Producto, Integer>(registro.getIdProducto(), detalle.getCantidad()));
			}

			Pedido pedido = new Pedido();
			pedido.setCantidad(detalle.getCantidad());
			pedido.setIdCliente(cliente);
			pedido.setIdProducto(registro.getIdProducto());
			pedido.setIdTienda(registro.getIdTienda());
			pedido.setFecha(new Date());
			pedidos.add(pedido);

		}

		for (String codigo : mapaProductoCantidad.keySet()) {
			Duplex<Producto, Integer> record = mapaProductoCantidad.get(codigo);

			Integer stockActual = record.getFirstElement().getStock();
			if (record.getSecondElement() >= stockActual) {
				Integer diferenciaStok = record.getSecondElement() - stockActual;

				if (diferenciaStok < 6) {
					// acept transacction and consume Mock Rest Service Asyncrhonus
					pedirUnidadesExtra(codigo);
					productoService.actualizarStockProducto(codigo, record.getSecondElement() * -1);
				} else if (diferenciaStok > 5 && diferenciaStok < 11) {
					if (1 == pedirMasUnidadesExtra(codigo)) {
						// acept transacction
						productoService.actualizarStockProducto(codigo, record.getSecondElement() * -1);
					}
				}
			} else {
				// update stock producto
				productoService.actualizarStockProducto(codigo, record.getSecondElement() * -1);
			}
		}

		pedidoService.guardarPedidos(pedidos);
		return ResponseEntity.status(HttpStatus.OK).body("El pedido se ha registrado exitosamente ");
	}

	private boolean validarDiferenciaMayorDisponible(Producto producto, Integer cantidadPed) {
		Integer diferenciaStok = cantidadPed - producto.getStock();
		if (diferenciaStok > 10) {
			return true;
		}
		return false;
	}

	@Async
	private void pedirUnidadesExtra(String codigoProducto) {
		RestTemplate restTemplate = new RestTemplate();
		StockMockDto stock = restTemplate.getForObject(URL_STOCK_FIVE, StockMockDto.class);
		productoService.actualizarStockProducto(codigoProducto, stock.getStock());
	}

	private Integer pedirMasUnidadesExtra(String codigoProducto) {
		RestTemplate restTemplate = new RestTemplate();
		StockMockDto stock = restTemplate.getForObject(URL_STOCK_TEN, StockMockDto.class);
		return productoService.actualizarStockProducto(codigoProducto, stock.getStock());
	}

	/**
	 * Endpoint for get all registers of Productos, dont need token for use
	 * 
	 * @return
	 */
	@RequestMapping(value = "/numero-transacciones", method = RequestMethod.GET)
	public List<InformacionTransaccionesRequestDto> getTransaccionesAgrupadas() {
		return pedidoService.buscarTransaccionesAgrupadasPorFechaTienda();
	}

	@RequestMapping(value = "/monto-vendido", method = RequestMethod.GET)
	public List<InformacionMontoVendidoRequestDto> getMontoVendido() {
		return pedidoService.buscarMontoVendidoPorTiendaProducto();
	}
}
