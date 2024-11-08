package com.componente.factinven.repositorios;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

import com.componente.factinven.entidades.OdontogramaRespuestas;

@Repository
public interface OdontogramaRespuestasRepositorio extends JpaRepository<OdontogramaRespuestas, Integer> {

	@Query("Select (respuesta) from OdontogramaRespuestas respuesta where cliente.id =  :clienteId order by respuesta.id ")
   	List<OdontogramaRespuestas> encontrarOdontogramaRepuestas(@Param("clienteId") Integer clienteId);
}
