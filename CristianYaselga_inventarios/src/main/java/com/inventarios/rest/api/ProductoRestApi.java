package com.inventarios.rest.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inventarios.dto.ProductoRequestDto;
import com.inventarios.dto.ProductoResponseDto;
import com.inventarios.jpa.entity.Producto;
import com.inventarios.services.ProductoService;

/**
 * Rest controller for PUT, GET Productos
 * 
 * @author CRIS
 *
 */
@RestController
public class ProductoRestApi {

	@Autowired
	ProductoService productoService;

	/**
	 * Endpoint for get all registers of Productos, dont need token for use
	 * 
	 * @return
	 */
	@RequestMapping(value = "/producto", method = RequestMethod.GET)
	public List<ProductoResponseDto> getProductos() {
		List<Producto> productos = productoService.getAll();
		List<ProductoResponseDto> response = new ArrayList<>();
		if(productos != null && !productos.isEmpty()) {
			productos.stream().forEach(p-> response.add(new ProductoResponseDto(p.getCodigo(),p.getNombre())));
		}
		return response;
	}

	/**
	 * Endpoint for modify a stock of Producto, dont need token for use
	 * 
	 * @param customer
	 * @return
	 */
	@RequestMapping(value = "/producto", method = RequestMethod.PUT)
	public ResponseEntity<String> actualizarStockProducto(@RequestBody ProductoRequestDto request) {
		if (request == null || request.getCodigo() == null || request.getStock() == null
				|| request.getStock() < 1 || request.getCodigo().isEmpty()) {
			return ResponseEntity.badRequest().body(
					"Datos Incompletos: Para actualizar el stock de un producto, debe ingresa un codigo, y stock mayor a 0");
		}
		Producto producto = productoService.buscarProductosPorCodigo(request.getCodigo());
		if (producto == null ) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					"El producto con codigo " + request.getCodigo() + " no se encuentra registrado");
		}
		producto.setStock(request.getStock());
		productoService.save(producto);
		return ResponseEntity.status(HttpStatus.OK).body("Stock de producto actualizado exitosamente ");
	}


}
