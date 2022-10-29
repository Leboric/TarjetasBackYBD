package com.tarjetas.DTO;

public class EnrolarDTO {
	Long numeroValidacion;
	String identificador;
	public Long getNumeroValidacion() {
		return numeroValidacion;
	}
	public void setNumeroValidacion(Long numeroValidacion) {
		this.numeroValidacion = numeroValidacion;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public EnrolarDTO(Long numeroValidacion, String identificador) {
		super();
		this.numeroValidacion = numeroValidacion;
		this.identificador = identificador;
	}
	public EnrolarDTO() {
		super();
	}
	
	
}
