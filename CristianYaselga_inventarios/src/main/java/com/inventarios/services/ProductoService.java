package com.inventarios.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventarios.jpa.dao.ProductoDao;
import com.inventarios.jpa.entity.Producto;

/**
 * Transactional Service to access the to transacctions of ProductoDao
 * 
 * @author CRIS
 *
 */
@Service
@Transactional
public class ProductoService {

	@Autowired
	ProductoDao productoDao;

	
	
	public void save(Producto produto) {
		productoDao.save(produto);
	}

	
	/**
	 * Save List of Producto
	 * @param productos
	 */
	public void guardarProductos(List<Producto> productos) {
		if(productos != null && !productos.isEmpty()) {
			productos.stream().forEach(p -> save(p));
		}
	}
	
	/**
	 * Get all records of Producto 
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Producto> getAll() {
		return productoDao.findAll();
	}
	
	/**
	 *  Get  Producto by codigo, if not exist return null
	 *  
	 * @param codigo
	 * @return
	 */
	@Transactional(readOnly = true)
	public Producto buscarProductosPorCodigo(String codigo) {
		List<Producto> result =productoDao.buscarProductosPorCodigo(codigo);
		if(result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	
	public int actualizarStockProducto(String codigo, Integer stockAdicional) {
		return productoDao.actualizarStockProducto(codigo, stockAdicional);
	}

}
