package com.inventarios.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity than Maping a TIENDA_PRODUCTO table
 * @author CRIS
 *
 */
@Entity
@Table(name="TIENDA_PRODUCTO", schema="public")
public class TiendaProducto  implements Serializable {

	private static final long serialVersionUID = -7470128616746259015L;

	@Id
	@Column(name="id_tienda_producto")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idTiendaProducto;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_tienda")
	private Tienda idTienda;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_producto")
	private Producto idProducto;

	/*GETTERS & SETTERS*/
	public Integer getIdTiendaProducto() {
		return idTiendaProducto;
	}

	public void setIdTiendaProducto(Integer idTiendaProducto) {
		this.idTiendaProducto = idTiendaProducto;
	}

	public Tienda getIdTienda() {
		return idTienda;
	}

	public void setIdTienda(Tienda idTienda) {
		this.idTienda = idTienda;
	}

	public Producto getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Producto idProducto) {
		this.idProducto = idProducto;
	}
}
