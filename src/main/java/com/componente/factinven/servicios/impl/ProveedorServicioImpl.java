package com.componente.factinven.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.componente.factinven.entidades.Proveedor;
import com.componente.factinven.repositorios.ProveedorRepositorio;
import com.componente.factinven.repositorios.ProveedorRepositorio.ProveedorNameAdres;
import com.componente.factinven.servicios.interfaz.IProveedorServicio;

@Service
public class ProveedorServicioImpl  implements IProveedorServicio {

	@Autowired
	private ProveedorRepositorio proveedorRepositorio;
	
	
	@Override
	public void guardarProveedor(Proveedor Proveedor) {
		proveedorRepositorio.save(Proveedor);
	}


	@Override
	public void eliminarProveedor(Proveedor Proveedor) {
		
	}


	@Override
	public Page<Proveedor> listarProveedors(int page, int size) {
		Pageable pages = PageRequest.of(page, size);
		return this.proveedorRepositorio.findAll(pages);
	}


	@Override
	public void editarProveedor(Proveedor Proveedor) {
		// TODO Auto-generated method stub
		this.proveedorRepositorio.save(Proveedor);
	}


	@Override
	public void borrarProveedors() {
		// TODO Auto-generated method stub
         this.proveedorRepositorio.deleteAll();		
	}


	@Override
	public List<Proveedor> buscarProveedorXNombre(String nombre) {
	 return this.proveedorRepositorio.findByNombreContainingIgnoreCase(nombre);
	}
    
	
	@Override
	public  List<ProveedorNameAdres> listarProveedor() {
		return proveedorRepositorio.llenarLista();
	}

}
