package com.inventarios.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inventarios.jpa.entity.TiendaProducto;

/**
 * Repository for opperations in TiendaProducto Entity
 * @author CRIS
 *
 */
public interface TiendaProductoDao extends JpaRepository<TiendaProducto, Integer>{

	@Query("SELECT a FROM TiendaProducto a WHERE a.idTienda.idTienda = ?1 ")
	List<TiendaProducto> buscarProductosPorTienda(String idTienda);
	
	@Query("SELECT a FROM TiendaProducto a WHERE a.idProducto.idProducto = ?1 ")
	List<TiendaProducto> buscarTiendasPorProducto(String idProducto);
	
	@Query("SELECT a FROM TiendaProducto a WHERE a.idTienda.idTienda = ?1 AND a.idProducto.idProducto = ?2 ")
	List<TiendaProducto> buscarRegistrosPorTiendaProducto(Integer idTienda, Integer idProducto);
	
	@Query("SELECT a FROM TiendaProducto a WHERE a.idTienda.codigo = ?1 AND a.idProducto.codigo = ?2 ")
	List<TiendaProducto> buscarRegistrosPorCodigoTiendaProducto(String idTienda, String idProducto);
}
