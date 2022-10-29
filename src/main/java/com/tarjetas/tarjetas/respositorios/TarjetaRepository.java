package com.tarjetas.tarjetas.respositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tarjetas.tarjetas.entidades.Tarjeta;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, String>{
	
	Optional <Tarjeta> findByIdentificador (String Identificador);

}
