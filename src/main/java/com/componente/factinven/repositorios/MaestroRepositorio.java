package com.componente.factinven.repositorios;

import com.componente.factinven.entidades.Maestro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaestroRepositorio extends JpaRepository<Maestro, Integer>
{

}
