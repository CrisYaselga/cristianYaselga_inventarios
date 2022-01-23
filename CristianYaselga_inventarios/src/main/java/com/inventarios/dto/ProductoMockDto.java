package com.inventarios.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO that wrap response of producto Mock service
 * @author CRIS
 *
 */
public class ProductoMockDto implements Serializable{

	private static final long serialVersionUID = -8121130277355824149L;

	private Integer id;
	
	private String cod;
	
	private String name;
	
	private BigDecimal price;
	
	private Integer stock;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "ProductoMockDto [id=" + id + ", cod=" + cod + ", name=" + name + ", price=" + price + ", stock=" + stock
				+ "]";
	}

	
}
