package com.tarjetas.tarjetas.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Response {

	@JsonProperty("datos")
	public Object datos;
	@JsonProperty("resultado")
	public Result result; 
	
}
