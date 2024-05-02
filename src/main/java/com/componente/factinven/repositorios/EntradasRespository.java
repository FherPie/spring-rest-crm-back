package com.componente.factinven.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.componente.factinven.entidades.Entrada;

@Repository
public interface EntradasRespository extends JpaRepository<Entrada, Integer> {

    
    @Query("Select (entrada) from Entrada entrada where MONTH(entrada.createdDate) = :createdMonth")
    List<Entrada> entradasMes(@Param("createdMonth") int createdMonth);
    
    
    
    
}
