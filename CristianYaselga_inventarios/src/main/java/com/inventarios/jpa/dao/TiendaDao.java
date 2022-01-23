package com.inventarios.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inventarios.jpa.entity.Tienda;

/**
 * Repository for opperations in Tienda Entity
 * @author CRIS
 *
 */
public interface TiendaDao extends JpaRepository<Tienda, Integer>{

	@Query("SELECT a FROM Tienda a WHERE a.codigo = ?1 ")
	List<Tienda> buscarTiendaPorCodigo(String codigo);
}
