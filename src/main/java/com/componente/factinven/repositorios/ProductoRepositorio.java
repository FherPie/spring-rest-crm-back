package com.componente.factinven.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.componente.factinven.entidades.Producto;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {
	
	public List<Producto> findByNombreContainingIgnoreCase(String nombre);
	public List<Producto> findByServdeOdontogramaTrue();
}
