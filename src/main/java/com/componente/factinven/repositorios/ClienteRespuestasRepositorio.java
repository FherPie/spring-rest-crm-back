package com.componente.factinven.repositorios;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.componente.factinven.entidades.ClienteRespuestas;
@Repository
public interface ClienteRespuestasRepositorio extends JpaRepository<ClienteRespuestas, Integer> {

	@Query("Select (respuesta) from ClienteRespuestas respuesta where cliente.id = :clienteId")
	List<ClienteRespuestas> encontrarClientesRepuestas(@Param("clienteId") Integer clienteId);
}
