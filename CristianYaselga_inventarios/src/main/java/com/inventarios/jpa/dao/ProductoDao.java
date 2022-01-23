package com.inventarios.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.inventarios.jpa.entity.Producto;

/**
 * Repository for opperations in Producto Entity
 * @author CRIS
 *
 */
public interface ProductoDao extends JpaRepository<Producto, Integer>{

	@Query("SELECT a FROM Producto a WHERE a.codigo = ?1 ")
	List<Producto> buscarProductosPorCodigo(String codigo);

	@Modifying
	@Query("update Producto a set a.stock = ?2 +a.stock where a.codigo = ?1")
	int actualizarStockProducto(String codigo, Integer stockAdicional);
}
