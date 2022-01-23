package com.inventarios.dto;

import java.io.Serializable;

/**
 * DTO that wrap response of producto
 * @author CRIS
 *
 */
public class ProductoResponseDto implements Serializable{

	private static final long serialVersionUID = -6311804291629336821L;

	private String codigo;
	
	private String nombre;

	public ProductoResponseDto(String codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
