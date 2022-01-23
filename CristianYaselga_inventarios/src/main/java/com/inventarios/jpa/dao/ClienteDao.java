package com.inventarios.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inventarios.jpa.entity.Cliente;

/**
 * Repository for opperations in Cliente Entity
 * @author CRIS
 *
 */
public interface ClienteDao extends JpaRepository<Cliente, Integer>{

	@Query("SELECT a FROM Cliente a WHERE a.identificacion = ?1")
	List<Cliente> buscarClientePorIdentificacion(String identificacion);
	
	@Query("SELECT a FROM Cliente a WHERE a.identificacion = ?1 AND a.password = ?2 ")
	List<Cliente> buscarClientePorIdentificacionPasword(String identificacion, String password);
}
