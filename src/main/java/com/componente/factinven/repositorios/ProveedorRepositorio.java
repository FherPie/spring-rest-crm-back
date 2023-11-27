package com.componente.factinven.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.componente.factinven.entidades.Proveedor;

@Repository
public interface ProveedorRepositorio extends JpaRepository<Proveedor, Integer> {


    @Transactional(readOnly = true)
	public List<Proveedor> findByNombreContainingIgnoreCase(String nombre);

	@Transactional(readOnly = true)
	@Query("Select  p.nombre, p.direccion from Proveedor p")
	public List<ProveedorNameAdres> llenarLista();


	public static interface ProveedorNameAdres {
		String getNombre();
		int getDireccion();
	}
}
