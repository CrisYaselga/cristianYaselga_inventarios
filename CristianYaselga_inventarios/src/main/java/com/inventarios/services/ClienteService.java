package com.inventarios.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventarios.jpa.dao.ClienteDao;
import com.inventarios.jpa.entity.Cliente;

/**
 * Transactional Service to access the to transacctions of ClienteDao
 * 
 * @author CRIS
 *
 */
@Service
@Transactional
public class ClienteService {

	@Autowired
	ClienteDao clienteDao;

	public void save(Cliente cliente) {
		clienteDao.save(cliente);
	}
	
	

	public void delete(Cliente cliente) {
		clienteDao.delete(cliente);
	}
	
	/**
	 * Get a Cliente by Password if dont exists return null
	 * 
	 * @param password
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Cliente> buscarClientePorIdentificacion(String identificacion) {
		if (identificacion == null || identificacion.isEmpty()) {
			return new ArrayList<>();
		}
		return clienteDao.buscarClientePorIdentificacion(identificacion);

	}

	/**
	 * Get a Cliente by Identificacion and Password if dont exists return null
	 * 
	 * @param identificacion
	 * @param password
	 * @return
	 */
	@Transactional(readOnly = true)
	public Cliente buscarClientePorIdentificacionPasword(String identificacion, String password) {
		if (identificacion == null || identificacion.isEmpty() || password == null || password.isEmpty()) {
			return null;
		}
		List<Cliente> customerResult = clienteDao.buscarClientePorIdentificacionPasword(identificacion, password);
		if (customerResult == null || customerResult.isEmpty()) {
			return null;
		}

		return customerResult.get(0);
	}

	
	public List<Cliente> getAll() {
		return clienteDao.findAll();
	}
}
