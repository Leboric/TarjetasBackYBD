package com.tarjetas.tarjetas.entidades;


import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tarjeta")
public class Tarjeta {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@Column(name = "identificador")
	String identificador;
	@Column(name = "numero_validacion")
	Long numeroValidacion;
	@Column(name = "pan")
	BigInteger pan;
	@Column(name = "pan_enmascarado")
	String panEnmascarado;
	@Column(name = "titular")
	String titular;
	@Column(name = "cedula")
	BigInteger cedula;
	@Column(name = "tipo")
	String tipo;
	@Column(name = "telefono")
	BigInteger telefono;
	@Column(name = "estados")
	String estados;
	@Column(name = "fecha_creacion")
	Date fechaCreacion;
	@Column(name = "fecha_modificacion")
	Date fechaModificacion;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public Long getNumeroValidacion() {
		return numeroValidacion;
	}
	public void setNumeroValidacion(Long numeroValidacion) {
		this.numeroValidacion = numeroValidacion;
	}
	public BigInteger getPan() {
		return pan;
	}
	public void setPan(BigInteger pan) {
		this.pan = pan;
	}
	public String getPanEnmascarado() {
		return panEnmascarado;
	}
	public void setPanEnmascarado(String panEnmascarado) {
		this.panEnmascarado = panEnmascarado;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public BigInteger getCedula() {
		return cedula;
	}
	public void setCedula(BigInteger cedula) {
		this.cedula = cedula;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public BigInteger getTelefono() {
		return telefono;
	}
	public void setTelefono(BigInteger telefono) {
		this.telefono = telefono;
	}
	public String getEstados() {
		return estados;
	}
	public void setEstados(String estados) {
		this.estados = estados;
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
	@Override
	public String toString() {
		return "Tarjeta [id=" + id + ", identificador=" + identificador + ", numeroValidacion=" + numeroValidacion
				+ ", pan=" + pan + ", panEnmascarado=" + panEnmascarado + ", titular=" + titular + ", cedula=" + cedula
				+ ", tipo=" + tipo + ", telefono=" + telefono + ", estados=" + estados + ", fechaCreacion="
				+ fechaCreacion + ", fechaModificacion=" + fechaModificacion + "]";
	}
	
	
	
	
	
}
