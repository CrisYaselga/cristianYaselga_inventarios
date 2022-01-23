package com.inventarios.jpa.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inventarios.dto.InformacionMontoVendidoRequestDto;
import com.inventarios.dto.InformacionTransaccionesRequestDto;
import com.inventarios.jpa.entity.Pedido;

/**
 * Repository for opperations in Pedido Entity
 * @author CRIS
 *
 */
public interface PedidoDao extends JpaRepository<Pedido, Integer>{

	@Query("SELECT a FROM Pedido a WHERE a.idCliente.idCliente = ?1 AND a.fecha >= ?2 AND a.fecha <= ?3 ")
	List<Pedido> buscarPedidosPorClienteEntreFechas(String idCliente, Date fechaInicial, Date fechaFinal);
	
	@Query("SELECT new com.inventarios.dto.InformacionTransaccionesRequestDto(count(a.id), a.idTienda.codigo, DATE_TRUNC('day',a.fecha)) FROM Pedido a GROUP BY a.idTienda.codigo, DATE_TRUNC('day',a.fecha) ")
	List<InformacionTransaccionesRequestDto> buscarTransaccionesAgrupadasPorFechaTienda();
	

	@Query("SELECT new com.inventarios.dto.InformacionMontoVendidoRequestDto(SUM(a.cantidad*a.idProducto.precio), a.idTienda.codigo, a.idProducto.codigo) FROM Pedido a GROUP BY  a.idTienda.codigo, a.idProducto.codigo ")
	List<InformacionMontoVendidoRequestDto> buscarMontoVendidoPorTiendaProducto();
}
