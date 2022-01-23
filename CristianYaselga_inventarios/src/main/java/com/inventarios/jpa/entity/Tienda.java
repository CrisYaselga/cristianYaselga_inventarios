package com.inventarios.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity than Maping a TIENDA table
 * @author CRIS
 *
 */
@Entity
@Table(name="TIENDA", schema="public")
public class Tienda implements Serializable {

	private static final long serialVersionUID = -7470128616746259015L;

	@Id
	@Column(name="id_tienda")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idTienda;

	@Column(name="codigo")
	private String codigo;


	@Column(name="nombre")
	private String nombre;

	/*GETTERS & SETTERS*/
	public String getCodigo() {
		return codigo;
	}

	public Integer getIdTienda() {
		return idTienda;
	}

	public void setIdTienda(Integer idTienda) {
		this.idTienda = idTienda;
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
