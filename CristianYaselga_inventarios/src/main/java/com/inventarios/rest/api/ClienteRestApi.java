package com.inventarios.rest.api;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventarios.jpa.entity.Cliente;
import com.inventarios.services.ClienteService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Rest controller for ADD, PUT, DELETE, GET, LOGIN  of a Cliente
 * @author CRIS
 *
 */
@RestController
public class ClienteRestApi {

	@Autowired
	ClienteService clienteService;

	
	private final String SECRET_KEY = "CYInventarios";

	/**
	 * Endpoint for get all registers of Cliente, dont need token for use
	 * @return
	 */
	@RequestMapping(value = "/cliente", method = RequestMethod.GET)
	public List<Cliente> getCustomer() {
			return clienteService.getAll();
	}
	
	/**
	 * Endpoint for save a new Cliente, dont need token for use
	 * @param customer
	 * @return
	 */
	@RequestMapping(value = "/cliente", method = RequestMethod.POST)
	public ResponseEntity<String> crearCliente(@RequestBody Cliente cliente) {
		if(cliente == null || cliente.getIdentificacion() == null || cliente.getPassword()== null || cliente.getNombre()== null
				|| cliente.getIdentificacion().isEmpty() || cliente.getPassword().isEmpty() || cliente.getNombre().isEmpty()) {
			return ResponseEntity.badRequest()
		            .body("Datos Incompletos: Para crear un cliente debe ingresar: Identificacion, Password y Nombre");
		}
		List<Cliente> clienteBdd = clienteService.buscarClientePorIdentificacion(cliente.getIdentificacion());
		if(clienteBdd != null && !clienteBdd.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		            .body("El cliente con identificacion "+cliente.getIdentificacion()+" ya se encuentra registrado");
		}
		clienteService.save(cliente);
		 return ResponseEntity.status(HttpStatus.OK)
			        .body("Cliente creado exitosamente ");
	}
	
	/**
	 * Endpoint for modify a  Cliente, dont need token for use
	 * @param customer
	 * @return
	 */
	@RequestMapping(value = "/cliente", method = RequestMethod.PUT)
	public ResponseEntity<String> actualizarCliente(@RequestBody Cliente cliente) {
		if(cliente == null || cliente.getIdentificacion() == null || cliente.getPassword()== null || cliente.getNombre()== null
				|| cliente.getIdentificacion().isEmpty() || cliente.getPassword().isEmpty() || cliente.getNombre().isEmpty()) {
			return ResponseEntity.badRequest()
		            .body("Datos Incompletos: Para actualizar un cliente debe ingresar: Identificacion, Password y Nombre");
		}
		List<Cliente> clienteBdd = clienteService.buscarClientePorIdentificacion(cliente.getIdentificacion());
		if(clienteBdd == null || clienteBdd.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		            .body("El cliente con identificacion "+cliente.getIdentificacion()+" no se encuentra registrado");
		}
		Cliente registroBdd =clienteBdd.get(0);
		registroBdd.setFoto(cliente.getFoto());
		registroBdd.setNombre(cliente.getNombre());
		registroBdd.setPassword(cliente.getPassword());
		
		clienteService.save(registroBdd);
		 return ResponseEntity.status(HttpStatus.OK)
			        .body("Cliente actualizado exitosamente ");
	}
	
	
	/**
	 * Endpoint for delete a Cliente, dont need token for use
	 * @param identificacion
	 * @return
	 */
	@RequestMapping(value = "/cliente", method = RequestMethod.DELETE)
	public ResponseEntity<String> eliminarCliente(@RequestBody String identificacion) {
		if(identificacion == null || identificacion.isEmpty()) {
			return ResponseEntity.badRequest()
		            .body("Ingrese una identificacion para elminar el cliente");
		}
		List<Cliente> clienteBdd = clienteService.buscarClientePorIdentificacion(identificacion);
		if(clienteBdd == null || clienteBdd.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		            .body("El cliente con identificacion "+identificacion+" no se encuentra registrado");
		}
		clienteService.delete(clienteBdd.get(0));
		 return ResponseEntity.status(HttpStatus.OK)
			        .body("Cliente eliminado exitosamente ");
	}
	
	/**
	 * Endpoint for Login a customer, dont need token for use
	 * @param customerAccount
	 * @param customerPassword
	 * @return
	 */
	@RequestMapping(value = "/clienteLogin", method = RequestMethod.POST)
	public Cliente login(@RequestParam String identificacion, @RequestParam String password) {
		Cliente customer = clienteService.buscarClientePorIdentificacionPasword(identificacion, password);
		//if customer exists, generate a token
		if (customer != null) {
			customer.setToken(getJWTToken(identificacion));
		}
		return customer;
	}


	
	/**
	 * Private Method for get Token by customerAccount
	 * @param customerAccount
	 * @return
	 */
	private String getJWTToken(String identificacion) {

		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		//Generated Token by CustomerAccount subject, and secretKey
		String token = Jwts.builder().setId("restAPI").setSubject(identificacion)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY.getBytes()).compact();

		return "Bearer " + token;

	}

}
