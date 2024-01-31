package com.componente.factinven.repositorios;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.componente.factinven.entidades.Comprobante;
import com.componente.factinven.entidades.Entrada;
import com.componente.factinven.entidades.Venta;

@Repository
public interface EntradasRespository extends JpaRepository<Entrada, Integer> {


}