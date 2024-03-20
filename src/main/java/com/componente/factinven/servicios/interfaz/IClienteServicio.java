package com.componente.factinven.servicios.interfaz;

import java.util.List;

import org.springframework.data.domain.Page;

import com.componente.factinven.dto.ClienteDto;
import com.componente.factinven.entidades.Cliente;

public interface IClienteServicio {

	public abstract  ClienteDto guardarCliente(ClienteDto cliente)  throws Exception ;
	
	public abstract  ClienteDto editarCliente(ClienteDto cliente) throws Exception;
	
	public abstract  void buscarClienteApellido(String apellido);
	
	public abstract  void eliminarCliente(ClienteDto cliente);
	
	public abstract  void borrarClientes();
	
	public List<ClienteDto> findAll();
	
	public List<ClienteDto> findAllPorApellidoContains(String apellidos);
	
	public ClienteDto findById(Integer idCliente );

	public abstract  Page<Cliente> listarClientes(int page, int size);
}
