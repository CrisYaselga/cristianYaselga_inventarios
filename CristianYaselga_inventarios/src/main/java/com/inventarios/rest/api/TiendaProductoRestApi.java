package com.inventarios.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inventarios.dto.TiendaProductoRequestDto;
import com.inventarios.jpa.entity.Producto;
import com.inventarios.jpa.entity.Tienda;
import com.inventarios.jpa.entity.TiendaProducto;
import com.inventarios.services.ProductoService;
import com.inventarios.services.TiendaProductoService;
import com.inventarios.services.TiendaService;

/**
 * Rest controller for POST, GET, DELETE Productos
 * 
 * @author CRIS
 *
 */
@RestController
public class TiendaProductoRestApi {

	@Autowired
	ProductoService productoService;
	
	@Autowired
	TiendaService tiendaService;
	
	@Autowired
	TiendaProductoService tiendaProductoService;

	
	
	/**
	 * Endpoint for create TiendaProducto, dont need token for use
	 * 
	 * @return
	 */
	@RequestMapping(value = "/tienda-producto", method = RequestMethod.POST)
	public ResponseEntity<String> asignarTiendaProducto(@RequestBody TiendaProductoRequestDto request) {
		if(request == null || request.getCodigoProducto()==null || request.getCodigoTienda()== null 
				|| request.getCodigoProducto().isEmpty() || request.getCodigoProducto().isEmpty()) {
			return ResponseEntity.badRequest()
		            .body("Datos Incompletos: Para realizar una asignacion debe ingresar: codigoTienda y codigoProducto");

		}
		Tienda tienda= tiendaService.buscarTiendaPorCodigo(request.getCodigoTienda());
		if(tienda == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		            .body("La tienda con codigo "+request.getCodigoTienda()+" no se encuentra registrada ");

		}
		
		Producto producto= productoService.buscarProductosPorCodigo(request.getCodigoProducto());
		if(producto == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		            .body("El producto con codigo "+request.getCodigoProducto()+" no se encuentra registrado ");

		}
		
		TiendaProducto registro= tiendaProductoService.buscarRegistroPorTiendaProducto(tienda.getIdTienda(), producto.getIdProducto());
		if(registro != null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		            .body("El producto con codigo "+request.getCodigoProducto()+" ya ha sido asignado a la tienda "+request.getCodigoTienda());

		}
		TiendaProducto nuevoRegistro = new TiendaProducto();
		nuevoRegistro.setIdProducto(producto);
		nuevoRegistro.setIdTienda(tienda);
		
		tiendaProductoService.save(nuevoRegistro);
		
		 return ResponseEntity.status(HttpStatus.OK)
			        .body("Asignacion de Tienda - Producto  exitosa ");
	}

	
	/**
	 * Endpoint for get delete TiendaProducto, dont need token for use
	 * 
	 * @return
	 */
	@RequestMapping(value = "/tienda-producto", method = RequestMethod.DELETE)
	public ResponseEntity<String> quitarTiendaProducto(@RequestBody TiendaProductoRequestDto request) {
		if(request == null || request.getCodigoProducto()==null || request.getCodigoTienda()== null 
				|| request.getCodigoProducto().isEmpty() || request.getCodigoProducto().isEmpty()) {
			return ResponseEntity.badRequest()
		            .body("Datos Incompletos: Para realizar una quitar la asignacion debe ingresar: codigoTienda y codigoProducto");

		}
		Tienda tienda= tiendaService.buscarTiendaPorCodigo(request.getCodigoTienda());
		if(tienda == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		            .body("La tienda con codigo "+request.getCodigoTienda()+" no se encuentra registrada ");

		}
		
		Producto producto= productoService.buscarProductosPorCodigo(request.getCodigoProducto());
		if(producto == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		            .body("El producto con codigo "+request.getCodigoProducto()+" no se encuentra registrado ");

		}
		
		TiendaProducto registro= tiendaProductoService.buscarRegistroPorTiendaProducto(tienda.getIdTienda(), producto.getIdProducto());
		if(registro == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		            .body("El producto con codigo "+request.getCodigoProducto()+" no ha sido asignado a la tienda "+request.getCodigoTienda());

		}
		
		
		tiendaProductoService.delete(registro);
		
		 return ResponseEntity.status(HttpStatus.OK)
			        .body("Se ha eliminado la Asignacion de Tienda - Producto  exitosamente ");
	}

}
