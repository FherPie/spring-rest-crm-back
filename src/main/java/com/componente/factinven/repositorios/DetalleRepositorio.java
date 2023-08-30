package com.componente.factinven.repositorios;

import com.componente.factinven.entidades.Detalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleRepositorio extends JpaRepository<Detalle, Integer>
{

}
