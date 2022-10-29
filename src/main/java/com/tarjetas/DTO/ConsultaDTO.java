package com.tarjetas.DTO;

import java.math.BigInteger;



public class ConsultaDTO {
	
	String pan;
	String titular;
	BigInteger cedula;
	BigInteger telefono;
	String estado;
	
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
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
	public BigInteger getTelefono() {
		return telefono;
	}
	public void setTelefono(BigInteger telefono) {
		this.telefono = telefono;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	
}
