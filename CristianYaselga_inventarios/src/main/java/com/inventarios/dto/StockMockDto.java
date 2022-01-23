package com.inventarios.dto;

import java.io.Serializable;

/**
 * DTO that wrap response of strock producto Mock service
 * @author CRIS
 *
 */
public class StockMockDto implements Serializable{

	private static final long serialVersionUID = -5805533927544235458L;

	private String code;
	
	private String name;
	
	private Integer stock;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "StockMockDto [code=" + code + ", name=" + name + ", stock=" + stock + "]";
	}


}
