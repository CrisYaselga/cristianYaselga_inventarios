package com.inventarios.dto;

import java.io.Serializable;

/**
 * DTO that wrap request of producto
 * @author CRIS
 *
 */
public class ProductoRequestDto implements Serializable{


	private static final long serialVersionUID = 6563601786239924098L;

	private String codigo;
	
	private Integer stock;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

}
