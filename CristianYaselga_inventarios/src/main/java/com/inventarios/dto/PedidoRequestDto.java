package com.inventarios.dto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO that wrap request of pedido
 * 
 * @author CRIS
 *
 */
public class PedidoRequestDto implements Serializable {

	private static final long serialVersionUID = -644989327914589263L;

	private String identificacionCliente;

	private List<PedidoDetalleRequestDto> detalle;

	public String getIdentificacionCliente() {
		return identificacionCliente;
	}

	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}

	public List<PedidoDetalleRequestDto> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<PedidoDetalleRequestDto> detalle) {
		this.detalle = detalle;
	}

}
