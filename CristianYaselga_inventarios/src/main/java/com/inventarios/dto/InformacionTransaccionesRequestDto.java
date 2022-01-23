package com.inventarios.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO that wrap response informacion transacciones 
 * 
 * @author CRIS
 *
 */
public class InformacionTransaccionesRequestDto implements Serializable {


	private static final long serialVersionUID = -4909135520099439381L;

	private Long numeroTransacciones;

	private String codigoTienda;
	
	private Date fecha;
	
	public InformacionTransaccionesRequestDto() {
		super();
	}

	public InformacionTransaccionesRequestDto(Long numeroTransacciones, String codigoTienda, Date fecha) {
		super();
		this.numeroTransacciones = numeroTransacciones;
		this.codigoTienda = codigoTienda;
		this.fecha = fecha;
	}

	public Long getNumeroTransacciones() {
		return numeroTransacciones;
	}

	public void setNumeroTransacciones(Long numeroTransacciones) {
		this.numeroTransacciones = numeroTransacciones;
	}

	public String getCodigoTienda() {
		return codigoTienda;
	}

	public void setCodigoTienda(String codigoTienda) {
		this.codigoTienda = codigoTienda;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
