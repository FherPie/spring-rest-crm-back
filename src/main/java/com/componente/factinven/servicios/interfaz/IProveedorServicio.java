package com.componente.factinven.servicios.interfaz;

import java.util.List;

import org.springframework.data.domain.Page;

import com.componente.factinven.entidades.Proveedor;
import com.componente.factinven.repositorios.ProveedorRepositorio.ProveedorNameAdres;

public interface IProveedorServicio {

	public abstract  void guardarProveedor(Proveedor Proveedor);
	
	public abstract  void editarProveedor(Proveedor Proveedor);
	
	public abstract  List<Proveedor> buscarProveedorXNombre(String nombre);
	
	public abstract  void eliminarProveedor(Proveedor Proveedor);
	
	public abstract  void borrarProveedors();

	public abstract  Page<Proveedor> listarProveedors(int page, int size);

	public abstract  List<ProveedorNameAdres> listarProveedor();

	
}
