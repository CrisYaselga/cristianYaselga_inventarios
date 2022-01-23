package com.inventarios.dto;

import java.io.Serializable;

/**
 * DTO that wrap request of tienda-producto
 * 
 * @author CRIS
 *
 */
public class TiendaProductoRequestDto implements Serializable {

	private static final long serialVersionUID = -644989327914589263L;

	private String codigoTienda;

	private String codigoProducto;

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

}
