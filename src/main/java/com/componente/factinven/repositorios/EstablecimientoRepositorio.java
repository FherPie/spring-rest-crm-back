package com.componente.factinven.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.componente.factinven.entidades.Establecimiento;

@Repository
public interface EstablecimientoRepositorio extends JpaRepository<Establecimiento, Integer> {


//    @Transactional(readOnly = true)
//	public List<Proveedor> findByNombreContainingIgnoreCase(String nombre);
//
//	@Transactional(readOnly = true)
//	@Query("Select  p.nombre, p.direccion from Proveedor p")
//	public List<ProveedorNameAdres> llenarLista();
//
//
//	public static interface ProveedorNameAdres {
//		String getNombre();
//		int getDireccion();
//	}
}
