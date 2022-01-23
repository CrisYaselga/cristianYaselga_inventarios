package com.inventarios.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO that wrap response informacion monto vendido
 * 
 * @author CRIS
 *
 */
public class InformacionMontoVendidoRequestDto implements Serializable {


	private static final long serialVersionUID = -4909135520099439381L;

	private BigDecimal montoVendido;

	private String codigoTienda;
	
	private String codigoProducto;

	
	public InformacionMontoVendidoRequestDto() {
		super();
	}

	public InformacionMontoVendidoRequestDto(BigDecimal montoVendido, String codigoTienda, String codigoProducto) {
		super();
		this.montoVendido = montoVendido;
		this.codigoTienda = codigoTienda;
		this.codigoProducto = codigoProducto;
	}

	public BigDecimal getMontoVendido() {
		return montoVendido;
	}

	public void setMontoVendido(BigDecimal montoVendido) {
		this.montoVendido = montoVendido;
	}

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
