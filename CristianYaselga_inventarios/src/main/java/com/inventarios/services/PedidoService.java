package com.inventarios.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventarios.dto.InformacionMontoVendidoRequestDto;
import com.inventarios.dto.InformacionTransaccionesRequestDto;
import com.inventarios.jpa.dao.PedidoDao;
import com.inventarios.jpa.entity.Pedido;

/**
 * Transactional Service to access the to transacctions of PedidoDao
 * 
 * @author CRIS
 *
 */
@Service
@Transactional
public class PedidoService {

	@Autowired
	PedidoDao pedidoDao;

	public void save(Pedido Pedido) {
		pedidoDao.save(Pedido);
	}


	/**
	 * Save List of Pedido
	 * @param pedidos
	 */
	public void guardarPedidos(List<Pedido> pedidos) {
		if(pedidos != null && !pedidos.isEmpty()) {
			pedidos.stream().forEach(p -> save(p));
		}
	}
	/**
	 * Get a Pedido by Cliente, FechaInicial, FechaFinal if dont exists return null
	 * 
	 * @param password
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Pedido> buscarPedidosPorClienteEntreFechas(String cliente, Date fechaInicial, Date fechaFinal) {
		if (cliente == null || cliente.isEmpty()) {
			return null;
		}
		List<Pedido> pedidoResult = pedidoDao.buscarPedidosPorClienteEntreFechas(cliente,fechaInicial,fechaFinal);
		if (pedidoResult == null || pedidoResult.isEmpty()) {
			return null;
		}

		return pedidoResult;
	}

	
	@Transactional(readOnly = true)
	public List<InformacionTransaccionesRequestDto> buscarTransaccionesAgrupadasPorFechaTienda(){
		return pedidoDao.buscarTransaccionesAgrupadasPorFechaTienda();
	}
	

	@Transactional(readOnly = true)
	public List<InformacionMontoVendidoRequestDto> buscarMontoVendidoPorTiendaProducto(){
		return pedidoDao.buscarMontoVendidoPorTiendaProducto();
	}
}
