package com.tarjetas.tarjetas.entidades;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transacciones")
public class Transaccion {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@Column(name = "referencia")
	Integer numeroReferencia;
	@Column(name = "identificador")
	String identificador;
	@Column(name = "fecha_creacion")
	Date fechaCreacion;
	@Column(name = "fecha_modificacion")
	Date fechaModificacion;
	@Column(name = "estado")
	String estado;
	@Column(name = "total")
	Integer total;
	@Column(name = "direccion_compra")
	String direccionCompra;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumeroReferencia() {
		return numeroReferencia;
	}
	public void setNumeroReferencia(Integer numeroReferencia) {
		this.numeroReferencia = numeroReferencia;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getDireccionCompra() {
		return direccionCompra;
	}
	public void setDireccionCompra(String direccionCompra) {
		this.direccionCompra = direccionCompra;
	}
	@Override
	public String toString() {
		return "Transaccion [id=" + id + ", numeroReferencia=" + numeroReferencia + ", identificador=" + identificador
				+ ", fechaCreacion=" + fechaCreacion + ", fechaModificacion=" + fechaModificacion + ", estado=" + estado
				+ ", total=" + total + ", direccionCompra=" + direccionCompra + "]";
	}
	
	
}
