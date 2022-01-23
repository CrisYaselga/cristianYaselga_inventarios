package com.inventarios.dto;

import java.io.Serializable;

/**
 * DTO that wrap request of pedido
 * 
 * @author CRIS
 *
 */
public class PedidoDetalleRequestDto implements Serializable {


	private static final long serialVersionUID = -4909135520099439381L;

	private String codigoProducto;

	private String codigoTienda;
	
	private Integer cantidad;

	public String getCodigoTienda() {
		return codigoTienda;
	}

	public void setCodigoTienda(String codigoTienda) {
		this.codigoTienda = codigoTienda;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}
