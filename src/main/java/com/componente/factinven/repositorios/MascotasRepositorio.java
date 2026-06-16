package com.componente.factinven.repositorios;

import com.componente.factinven.dto.VentaResponse;
import com.componente.factinven.entidades.Mascotas;
import com.componente.factinven.entidades.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MascotasRepositorio extends JpaRepository<Mascotas, Integer> {

}
