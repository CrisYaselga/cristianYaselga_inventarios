package com.inventarios.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventarios.jpa.dao.TiendaProductoDao;
import com.inventarios.jpa.entity.TiendaProducto;

/**
 * Transactional Service to access the to transacctions of TiendaProductoDao
 * 
 * @author CRIS
 *
 */
@Service
@Transactional
public class TiendaProductoService {

	@Autowired
	TiendaProductoDao tiendaProductoDao;

	public void save(TiendaProducto registro) {
		tiendaProductoDao.save(registro);
	}
	
	public void delete(TiendaProducto registro) {
		tiendaProductoDao.delete(registro);
	}

	/**
	 * Get list of Producto by Tienda if dont exists return null
	 * 
	 * @param tienda
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<TiendaProducto> buscarProductosPorTienda(String tienda) {
		if (tienda == null || tienda.isEmpty()) {
			return null;
		}
		List<TiendaProducto> result = tiendaProductoDao.buscarProductosPorTienda(tienda);
		if (result == null || result.isEmpty()) {
			return null;
		}

		return result;
	}

	/**
	 * Get list of Tienda by Producto if dont exists return null
	 * 
	 * @param producto
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<TiendaProducto> buscarTiendasPorProducto(String producto) {
		if (producto == null || producto.isEmpty()) {
			return null;
		}
		List<TiendaProducto> result = tiendaProductoDao.buscarTiendasPorProducto(producto);
		if (result == null || result.isEmpty()) {
			return null;
		}

		return result;
	}

	/**
	 * Get Register by tienda and producto, if dont exist return null
	 * 
	 * @param codigoTienda
	 * @param codigoProducto
	 * @return
	 */
	@Transactional(readOnly = true)
	public TiendaProducto buscarRegistroPorTiendaProducto(Integer idTienda, Integer idProducto) {
		List<TiendaProducto> result = tiendaProductoDao.buscarRegistrosPorTiendaProducto(idTienda, idProducto);
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public TiendaProducto buscarRegistroPorCodigoTiendaProducto(String codigoTienda, String codigoProducto) {
		List<TiendaProducto> result = tiendaProductoDao.buscarRegistrosPorCodigoTiendaProducto(codigoTienda, codigoProducto);
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	
}
