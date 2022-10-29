package com.tarjetas.tarjetas.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarjetas.DTO.EnrolarDTO;
import com.tarjetas.tarjetas.entidades.Response;
import com.tarjetas.tarjetas.entidades.Tarjeta;
import com.tarjetas.tarjetas.entidades.Transaccion;
import com.tarjetas.tarjetas.servicios.TarjetaService;

@RestController
@RequestMapping("/tarjeta")
public class TarjetasControlador {
	
	@Autowired
	TarjetaService tarjetaService;

	@PostMapping("/registrar")
	public ResponseEntity<Response> registrar(@RequestBody Tarjeta tarjeta) {
		return tarjetaService.registrarTarjeta(tarjeta);
	}
	
	@PutMapping("/enrolar")
	public ResponseEntity<Response> enrolar(@RequestBody EnrolarDTO enrolarDTO) {
		return tarjetaService.enrolarTarjeta(enrolarDTO);
	}
	
	@GetMapping("/consultar")
	public ResponseEntity<Response> consultar(@RequestBody EnrolarDTO enrolarDTO) {
		return tarjetaService.consultarTarjeta(enrolarDTO);
	}
	
	@GetMapping("/consultar/todo")
	public ResponseEntity<Response> consultarTodo() {
		return tarjetaService.consultarTodo();
	}
	
	@PutMapping("/eliminar")
	public ResponseEntity<Response> eliminar(@RequestBody EnrolarDTO enrolarDTO) {
		return tarjetaService.eliminarTarjeta(enrolarDTO);
	}
	
	@PostMapping("/transaccion")
	public ResponseEntity<Response> transaccion(@RequestBody Transaccion transaccion) {
		return tarjetaService.transaccionTarjeta(transaccion);
	}
	
	@GetMapping("/transaccion/consultar")
	public ResponseEntity<Response> consultarTransaccion() {
		return tarjetaService.consultarTransaccion();
	}
	
	@PutMapping("/transaccion/cancelar")
	public ResponseEntity<Response> transaccionCancelar(@RequestBody Transaccion transaccion) {
		return tarjetaService.cancelarTransaccion(transaccion);
	}
}


