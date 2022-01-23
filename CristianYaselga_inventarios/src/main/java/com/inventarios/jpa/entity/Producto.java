package com.inventarios.jpa.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity than Maping a Producto table
 * @author CRIS
 *
 */
@Entity
@Table(name="PRODUCTO", schema="public")
public class Producto implements Serializable {

	private static final long serialVersionUID = -7470128616746259015L;

	@Id
	@Column(name="id_producto")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idProducto;

	@Column(name="codigo")
	private String codigo;

	@Column(name="nombre")
	private String nombre;

	@Column(name="precio")
	private BigDecimal precio;

	@Column(name="stock")
	private Integer stock;

	/*GETTERS & SETTERS*/
	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
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

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
}
