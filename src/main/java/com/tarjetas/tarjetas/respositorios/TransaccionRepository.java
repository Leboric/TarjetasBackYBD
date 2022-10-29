package com.tarjetas.tarjetas.respositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tarjetas.tarjetas.entidades.Tarjeta;
import com.tarjetas.tarjetas.entidades.Transaccion;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long>{
	
	Optional <Transaccion> findByNumeroReferenciaAndIdentificador (Integer numeroReferencia, String Identificador);

}
