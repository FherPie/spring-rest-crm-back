package com.componente.factinven.servicios.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.componente.factinven.dto.ClienteRequest;
import com.componente.factinven.dto.ClienteResponse;
import com.componente.factinven.dto.ProductoResponse;
import com.componente.factinven.entidades.Cliente;
import com.componente.factinven.entidades.Persona;
import com.componente.factinven.entidades.Producto;
import com.componente.factinven.repositorios.ClienteRepositorio;
import com.componente.factinven.repositorios.PersonaRepositorio;
import com.componente.factinven.servicios.interfaz.IClienteServicio;

@Service
public class ClienteServicioImpl  implements IClienteServicio {

	@Autowired
	private ClienteRepositorio clienteRepositorio;
	

	@Autowired
	private PersonaRepositorio personaRepositorio;
	
	
	@Override
	public ClienteResponse guardarCliente(ClienteRequest cliente) {
		Cliente clienteGuardar = new Cliente();
		Persona persona= new Persona();
		persona.setApellidos(cliente.getApellidos());
		persona.setNombres(cliente.getNombres());
		persona.setDireccion(cliente.getDireccion());
		persona.setEmail(cliente.getEmail());
		persona.setTelefono(cliente.getTelefono());
		persona.setIdentificacion(cliente.getIdentificacion());
		personaRepositorio.save(persona);
		clienteGuardar.setPersona(persona);
		//clienteGuardar.setCategoria(cliente.getCategoria());
	    return new ClienteResponse(clienteRepositorio.save(clienteGuardar));
	}


	@Override
	public void buscarClienteApellido(String apellido) {
		
		
	}


	@Override
	public void eliminarCliente(ClienteRequest Cliente) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Page<Cliente> listarClientes(int page, int size) {
		Pageable pages = PageRequest.of(page, size);
		return this.clienteRepositorio.findAll(pages);
	}


	@Override
	public ClienteResponse editarCliente(ClienteRequest cliente) {
		Cliente clienteGuardar = new Cliente();
		Persona persona= new Persona();
		persona.setApellidos(cliente.getApellidos());
		persona.setNombres(cliente.getNombres());
		persona.setDireccion(cliente.getDireccion());
		persona.setEmail(cliente.getEmail());
		persona.setTelefono(cliente.getTelefono());
		persona.setIdentificacion(cliente.getIdentificacion());
		clienteGuardar.setPersona(persona);
		clienteGuardar.setCategoria(cliente.getCategoria());
	    return new ClienteResponse(clienteRepositorio.save(clienteGuardar));
	}


	@Override
	public void borrarClientes() {
		// TODO Auto-generated method stub
         this.clienteRepositorio.deleteAll();		
	}


	@Override
	public List<ClienteResponse> findAll() {
		List<ClienteResponse> listaRetorno = this.clienteRepositorio.findAll().stream().map(x -> {
			return new ClienteResponse(x);
		}).collect(Collectors.toList());
		return listaRetorno;
	}


	@Override
	public ClienteResponse findById(Integer idCliente) {
		Cliente prod = this.clienteRepositorio.findById(idCliente).get();
		if (prod == null) {
			return null;
		}
		return new ClienteResponse(prod);
	}


	@Override
	public List<ClienteResponse> findAllPorApellidoContains(String apellidos) {
		List<ClienteResponse> listaRetorno = this.clienteRepositorio.findByPersonaApellidosContainingIgnoreCase(apellidos).stream().map(x -> {
			return new ClienteResponse(x);
		}).collect(Collectors.toList());
		return listaRetorno;
	}

}
