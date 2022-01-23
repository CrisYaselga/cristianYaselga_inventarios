package com.inventarios.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Entity than Maping a CLIENTE table
 * @author CRIS
 *
 */
@Entity
@Table(name="CLIENTE", schema="public")
public class Cliente implements Serializable {

	private static final long serialVersionUID = -7470128616746259015L;

	@Id
	@Column(name="id_cliente")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idCliente;

	@Column(name="nombre")
	private String nombre;

	@Column(name="identificacion")
	private String identificacion;

	@Column(name="foto")
	private String foto;

	@Column(name="password")
	private String password;
	
	@Transient
	private String token;

	/*GETTERS & SETTERS*/
	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
