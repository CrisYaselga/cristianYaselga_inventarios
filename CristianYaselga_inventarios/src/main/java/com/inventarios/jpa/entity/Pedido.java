package com.inventarios.jpa.entity;

import java.io.Serializable;
import java.util.Date;

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
 * Entity than Maping a PEDIDO table
 * @author CRIS
 *
 */
@Entity
@Table(name="PEDIDO", schema="public")
public class Pedido implements Serializable {

	private static final long serialVersionUID = -7470128616746259015L;

	@Id
	@Column(name="id_Pedido")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPedido;

	@Column(name="cantidad")
	private Integer cantidad;
	
	@Column(name="fecha")
	private Date fecha;


	//bi-directional many-to-one association to DeviceType
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_tienda")
	private Tienda idTienda;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_producto")
	private Producto idProducto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_cliente")
	private Cliente idCliente;

	/*GETTERS & SETTERS*/
	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}
}
