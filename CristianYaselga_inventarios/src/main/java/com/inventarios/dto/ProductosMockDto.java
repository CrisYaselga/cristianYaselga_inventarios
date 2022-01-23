package com.inventarios.dto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO that wrap response of List productos Mock service
 * @author CRIS
 *
 */
public class ProductosMockDto implements Serializable{
	private static final long serialVersionUID = -7415090805559142103L;

	private List<ProductoMockDto> prods;

	public List<ProductoMockDto> getProds() {
		return prods;
	}

	public void setProds(List<ProductoMockDto> prods) {
		this.prods = prods;
	}

	@Override
	public String toString() {
		return "ProductosMockDto [prods=" + prods + "]";
	}

}
