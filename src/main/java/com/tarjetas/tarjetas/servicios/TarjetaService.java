package com.tarjetas.tarjetas.servicios;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tarjetas.DTO.ConsultaDTO;
import com.tarjetas.DTO.EnrolarDTO;
import com.tarjetas.DTO.TarjetaDTO;
import com.tarjetas.DTO.TransaccionDTO;
import com.tarjetas.tarjetas.entidades.Response;
import com.tarjetas.tarjetas.entidades.Result;
import com.tarjetas.tarjetas.entidades.Tarjeta;
import com.tarjetas.tarjetas.entidades.Transaccion;
import com.tarjetas.tarjetas.respositorios.TarjetaRepository;
import com.tarjetas.tarjetas.respositorios.TransaccionRepository;

@Service
public class TarjetaService {
	@Autowired
	TarjetaRepository tarjetaRepository;

	@Autowired
	TransaccionRepository transaccionRepository;

	public ResponseEntity<Response> registrarTarjeta(Tarjeta tarjeta) {
		Response response = new Response();
		Result result = new Result();
		String panFinal = "";
		Integer cont = 0;
		Integer cont2 = 0;
		TarjetaDTO tarjetaDTO = new TarjetaDTO();
		try {

			Date fecha = new Date(Calendar.getInstance().getTimeInMillis());
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-hh:mm:ss");
			String fechaTexto = formatter.format(fecha);

			tarjeta.setFechaCreacion(fecha);
			tarjeta.setFechaModificacion(fecha);
			char[] lista = tarjeta.getPan().toString().toCharArray();
			Integer limit = lista.length - 10;
			for (int i = 0; i < lista.length; i++) {
				if (cont > 5 && cont2 < limit) {
					cont2++;
				} else {
					panFinal = panFinal + lista[i];
				}
				cont++;

			}
			String Identificador = panFinal + "-" + fechaTexto;
			tarjeta.setIdentificador(Identificador);

			Long numval = (long) (Math.random() * 100 + 1);

			tarjeta.setNumeroValidacion(numval);

			tarjeta.setEstados("CREADA");

			int length = String.valueOf(tarjeta.getPan()).length();
			if (length < 16 || length > 19) {
				result.setCode("01");
				result.setDescription("Fallido");
				response.setResult(result);
				response.setDatos("Pan fallido");
				return new ResponseEntity<Response>(response, HttpStatus.OK);
			}

			int tamaño = String.valueOf(tarjeta.getCedula()).length();
			if (tamaño < 10 || tamaño > 15) {
				result.setCode("01");
				result.setDescription("Fallido");
				response.setResult(result);
				response.setDatos("Cedula fallida");
				return new ResponseEntity<Response>(response, HttpStatus.OK);
			}

			int t = String.valueOf(tarjeta.getTelefono()).length();
			if (t != 10) {
				result.setCode("01");
				result.setDescription("Fallido");
				response.setResult(result);
				response.setDatos("Telefono fallido");
				return new ResponseEntity<Response>(response, HttpStatus.OK);
			}
			tarjeta.setPanEnmascarado(enmascararPan(tarjeta.getPan()));
			tarjetaRepository.save(tarjeta);

			tarjetaDTO.setNumeroValidacion(numval);
			tarjetaDTO.setPan(enmascararPan(tarjeta.getPan()));
			tarjetaDTO.setIdentificador(Identificador);

			result.setCode("00");
			result.setDescription("Exito");
			response.setResult(result);
			response.setDatos(tarjetaDTO);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} catch (Exception e) {

			result.setCode("01");
			result.setDescription("Fallido");
			response.setResult(result);
			response.setDatos(e.getMessage());
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}

	}

	public ResponseEntity<Response> enrolarTarjeta(EnrolarDTO enrolarDTO) {
		Response response = new Response();
		Result result = new Result();
		Tarjeta tarjeta = new Tarjeta();
		TarjetaDTO tarjetaDTO = new TarjetaDTO();
		Optional<Tarjeta> tarjetaOP = Optional.of(new Tarjeta());

		tarjetaOP = tarjetaRepository.findByIdentificador(enrolarDTO.getIdentificador());

		if (tarjetaOP.isPresent()) {
			tarjeta = tarjetaOP.get();
			if (tarjeta.getNumeroValidacion() == enrolarDTO.getNumeroValidacion()) {

				tarjeta.setEstados("ENROLADA");
				try {
					tarjetaRepository.save(tarjeta);

					tarjetaDTO.setPan(enmascararPan(tarjeta.getPan()));
					result.setCode("01");
					result.setDescription("Éxito");
					response.setResult(result);
					response.setDatos(tarjetaDTO);
					return new ResponseEntity<Response>(response, HttpStatus.OK);
				} catch (Exception e) {
					result.setCode("01");
					result.setDescription("Update Fallido");
					response.setResult(result);
					response.setDatos(e.getMessage());
					return new ResponseEntity<Response>(response, HttpStatus.OK);
				}

			} else {
				result.setCode("02");
				result.setDescription("Número de validación inválido");
				response.setResult(result);
				response.setDatos("");
				return new ResponseEntity<Response>(response, HttpStatus.OK);
			}

		} else {
			result.setCode("01");
			result.setDescription("Tarjeta no existe");
			response.setResult(result);
			response.setDatos(null);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}

	}

	public String enmascararPan(BigInteger pan) {
		String panFinal = "";
		Integer cont = 0;
		Integer cont2 = 0;
		char[] lista = pan.toString().toCharArray();
		Integer limit = lista.length - 10;
		for (int i = 0; i < lista.length; i++) {
			if (cont > 5 && cont2 < limit) {
				panFinal = panFinal + "*";
				cont2++;
			} else {
				panFinal = panFinal + lista[i];
			}
			cont++;

		}
		return panFinal;
	}

	public ResponseEntity<Response> consultarTarjeta(EnrolarDTO enrolarDTO) {
		Response response = new Response();
		Result result = new Result();
		Tarjeta tarjeta = new Tarjeta();
		Optional<Tarjeta> tarjetaOP = Optional.of(new Tarjeta());
		ConsultaDTO consulta = new ConsultaDTO();
		tarjetaOP = tarjetaRepository.findByIdentificador(enrolarDTO.getIdentificador());
		if (tarjetaOP.isPresent()) {
			tarjeta = tarjetaOP.get();

			consulta.setPan(enmascararPan(tarjeta.getPan()));
			consulta.setTitular(tarjeta.getTitular());
			consulta.setCedula(tarjeta.getCedula());
			consulta.setTelefono(tarjeta.getTelefono());
			consulta.setEstado(tarjeta.getEstados());

			result.setCode("01");
			result.setDescription("Éxito");
			response.setResult(result);
			response.setDatos(consulta);
			return new ResponseEntity<Response>(response, HttpStatus.OK);

		} else {
			result.setCode("01");
			result.setDescription("Tarjeta no existe");
			response.setResult(result);
			response.setDatos(null);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}

	}

	public ResponseEntity<Response> eliminarTarjeta(EnrolarDTO enrolarDTO) {
		Response response = new Response();
		Result result = new Result();
		Tarjeta tarjeta = new Tarjeta();
		TarjetaDTO tarjetaDTO = new TarjetaDTO();
		Optional<Tarjeta> tarjetaOP = Optional.of(new Tarjeta());

		tarjetaOP = tarjetaRepository.findByIdentificador(enrolarDTO.getIdentificador());

		if (tarjetaOP.isPresent()) {
			tarjeta = tarjetaOP.get();

			tarjeta.setFechaModificacion(new Date());
			tarjeta.setEstados("INACTIVA");
			try {
				tarjetaRepository.save(tarjeta);
				result.setCode("00");
				result.setDescription("Se ha eliminado la tarjeta");
				response.setResult(result);
				response.setDatos(null);
				return new ResponseEntity<Response>(response, HttpStatus.OK);
			} catch (Exception e) {
				result.setCode("01");
				result.setDescription("No se ha eliminado la tarjeta");
				response.setResult(result);
				response.setDatos(e.getMessage());
				return new ResponseEntity<Response>(response, HttpStatus.OK);
			}

		} else {
			result.setCode("01");
			result.setDescription("Tarjeta no existe");
			response.setResult(result);
			response.setDatos(null);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}

	}

	public ResponseEntity<Response> transaccionTarjeta(Transaccion transaccion) {
		Response response = new Response();
		Result result = new Result();
		Tarjeta tarjeta = new Tarjeta();
		TransaccionDTO transaccionDTO = new TransaccionDTO();
		Optional<Tarjeta> tarjetaOP = Optional.of(new Tarjeta());
		Optional<Transaccion> transaccionOP = Optional.of(new Transaccion());

		transaccionOP = transaccionRepository.findByNumeroReferenciaAndIdentificador(transaccion.getNumeroReferencia(),
				transaccion.getIdentificador());
		
		if (transaccionOP.isPresent()) {
			System.out.println(transaccionOP.get().toString());
			result.setCode("04");
			result.setDescription("Ya Existe Transaccion Con El Numero De Referencia");
			response.setResult(result);
			response.setDatos("");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {

			tarjetaOP = tarjetaRepository.findByIdentificador(transaccion.getIdentificador());

			if (tarjetaOP.isPresent()) {
				tarjeta = tarjetaOP.get();
				if (tarjeta.getEstados().equals("ENROLADA")) {

					try {
						
						int length = String.valueOf(transaccion.getNumeroReferencia()).length();
						
						if(length != 6) {
							
							result.setCode("03");
							result.setDescription("Numero de referencia invalida");
							response.setResult(result);
							response.setDatos("Invalida");
							return new ResponseEntity<Response>(response, HttpStatus.OK);
							
						}
						
						
						transaccion.setEstado("APROVADA");
						transaccion.setFechaCreacion(new Date());
						transaccion.setFechaModificacion(new Date());
						transaccionRepository.save(transaccion);

						transaccionDTO.setEstado("APROVADA");
						transaccionDTO.setNumeroReferencia(transaccion.getNumeroReferencia());


						
						result.setCode("00");
						result.setDescription("Éxito");
						response.setResult(result);
						response.setDatos(transaccionDTO);
						return new ResponseEntity<Response>(response, HttpStatus.OK);
						
						
					} catch (Exception e) {
						result.setCode("01");
						result.setDescription("Insert Fallido");
						response.setResult(result);
						response.setDatos(e.getMessage());
						return new ResponseEntity<Response>(response, HttpStatus.OK);
					}

				} else {
					transaccion.setEstado("RECHAZADA");
					transaccion.setFechaCreacion(new Date());
					transaccion.setFechaModificacion(new Date());
					transaccionRepository.save(transaccion);
					result.setCode("02");
					result.setDescription("Tarjeta no enrolada");
					response.setResult(result);
					response.setDatos("");
					return new ResponseEntity<Response>(response, HttpStatus.OK);
				}

			} else {
				result.setCode("01");
				result.setDescription("Tarjeta no existe");
				response.setResult(result);
				response.setDatos(null);
				return new ResponseEntity<Response>(response, HttpStatus.OK);
			}
		}
	}

	public ResponseEntity<Response> cancelarTransaccion(Transaccion transaccion) {
		Response response = new Response();
		Result result = new Result();
		Transaccion transaccionC = new Transaccion();
		TransaccionDTO transaccionDTO = new TransaccionDTO();
		Optional<Transaccion> transaccionOP = Optional.of(new Transaccion());

		transaccionOP = transaccionRepository.findByNumeroReferenciaAndIdentificador(transaccion.getNumeroReferencia(),
				transaccion.getIdentificador());

		if (transaccionOP.isPresent()) {
			transaccionC = transaccionOP.get();
			Integer tiempo = calcularTiempo(transaccionC.getFechaCreacion());
			if (tiempo <= 5) {

				try {

					transaccionC.setEstado("ANULADA");
					transaccionC.setFechaModificacion(new Date());
					transaccionRepository.save(transaccionC);

					transaccionDTO.setEstado("ANULADA");
					transaccionDTO.setNumeroReferencia(transaccion.getNumeroReferencia());

					result.setCode("00");
					result.setDescription("Compra anulada");
					response.setResult(result);
					response.setDatos(transaccionDTO);
					return new ResponseEntity<Response>(response, HttpStatus.OK);
				} catch (Exception e) {
					result.setCode("01");
					result.setDescription("Update Fallido");
					response.setResult(result);
					response.setDatos(e.getMessage());
					return new ResponseEntity<Response>(response, HttpStatus.OK);
				}

			} else {
				transaccionDTO.setNumeroReferencia(transaccion.getNumeroReferencia());
				result.setCode("02");
				result.setDescription("No se puede anular transacción");
				response.setResult(result);
				response.setDatos(transaccionDTO);
				return new ResponseEntity<Response>(response, HttpStatus.OK);
			}

		} else {
			transaccionDTO.setNumeroReferencia(transaccion.getNumeroReferencia());
			result.setCode("01");
			result.setDescription("numero de referencia inválido");
			response.setResult(result);
			response.setDatos(transaccionDTO);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
	}

	public Integer calcularTiempo(Date fechaCreacion) {
		Date fecha1 = new Date();
		long diferencia = fecha1.getTime() - fechaCreacion.getTime();
		Integer minutos = (int) TimeUnit.MILLISECONDS.toMinutes(diferencia);
		return minutos;
	}

	public ResponseEntity<Response> consultarTodo() {
		Response response = new Response();
		Result result = new Result();
		List<Tarjeta> tarjetas = new ArrayList<Tarjeta>();
		tarjetas = tarjetaRepository.findAll();
		result.setCode("00");
		result.setDescription("Exito");
		response.setResult(result);
		response.setDatos(tarjetas);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	public ResponseEntity<Response> consultarTransaccion() {
		Response response = new Response();
		Result result = new Result();
		List<Transaccion> transaccion = new ArrayList<Transaccion>();
		transaccion = transaccionRepository.findAll();
		result.setCode("00");
		result.setDescription("Exito");
		response.setResult(result);
		response.setDatos(transaccion);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
