package com.inventarios.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventarios.jpa.dao.TiendaDao;
import com.inventarios.jpa.entity.Tienda;

/**
 * Transactional Service to access the to transacctions of TiendaDao
 * 
 * @author CRIS
 *
 */
@Service
@Transactional
public class TiendaService {

	@Autowired
	TiendaDao tiendaDao;

	
	/**
	 * Get all records of Tienda 
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Tienda> getAll() {
		return tiendaDao.findAll();
	}
	
	/**
	 *  Get  Teinda by codigo, if not exist return null
	 *  
	 * @param codigo
	 * @return
	 */
	@Transactional(readOnly = true)
	public Tienda buscarTiendaPorCodigo(String codigo) {
		List<Tienda> result =tiendaDao.buscarTiendaPorCodigo(codigo);
		if(result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	

}
