package com.componente.factinven.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.componente.factinven.entidades.Salida;

@Repository
public interface SalidasRespository extends JpaRepository<Salida, Integer> {


	@Query("Select (salida) from Salida salida where  MONTH(salida.createdDate) = :createdMonth")
	List<Salida> salidasMes(@Param("createdMonth") int createdMonth);
	
}
