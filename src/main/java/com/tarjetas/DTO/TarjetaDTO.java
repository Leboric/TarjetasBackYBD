package com.tarjetas.DTO;

import java.math.BigInteger;

public class TarjetaDTO {
	Long numeroValidacion;
	String pan;
	String identificador;
	public Long getNumeroValidacion() {
		return numeroValidacion;
	}
	public void setNumeroValidacion(Long numeroValidacion) {
		this.numeroValidacion = numeroValidacion;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public TarjetaDTO() {
		super();
	}
	
	
}
