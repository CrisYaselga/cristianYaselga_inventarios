package com.inventarios.rest.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inventarios.jpa.entity.Tienda;
import com.inventarios.services.TiendaService;

/**
 * Rest controller for GET Tiendas
 * 
 * @author CRIS
 *
 */
@RestController
public class TiendaRestApi {

	@Autowired
	TiendaService tiendaService;

	/**
	 * Endpoint for get all registers of Tienda, dont need token for use
	 * 
	 * @return
	 */
	@RequestMapping(value = "/tienda", method = RequestMethod.GET)
	public List<Tienda> getTiendas() {
		return tiendaService.getAll();
	}

}
