package com.componente.factinven.servicios.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.componente.factinven.dto.ClienteDto;
import com.componente.factinven.dto.ProductoDto;
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
	public ClienteDto guardarCliente(ClienteDto cliente) {
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
	    return new ClienteDto(clienteRepositorio.save(clienteGuardar));
	}


	@Override
	public void buscarClienteApellido(String apellido) {
		
		
	}


	@Override
	public void eliminarCliente(ClienteDto Cliente) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Page<Cliente> listarClientes(int page, int size) {
		Pageable pages = PageRequest.of(page, size);
		return this.clienteRepositorio.findAll(pages);
	}


	@Override
	public ClienteDto editarCliente(ClienteDto cliente) {
		Cliente clienteGuardar = new Cliente();
		Persona persona= new Persona();
		persona.setApellidos(cliente.getApellidos());
		persona.setNombres(cliente.getNombres());
		persona.setDireccion(cliente.getDireccion());
		persona.setEmail(cliente.getEmail());
		persona.setTelefono(cliente.getTelefono());
		persona.setIdentificacion(cliente.getIdentificacion());
		clienteGuardar.setPersona(persona);
	    return new ClienteDto(clienteRepositorio.save(clienteGuardar));
	}


	@Override
	public void borrarClientes() {
		// TODO Auto-generated method stub
         this.clienteRepositorio.deleteAll();		
	}


	@Override
	public List<ClienteDto> findAll() {
		List<ClienteDto> listaRetorno = this.clienteRepositorio.findAll().stream().map(x -> {
			return new ClienteDto(x);
		}).collect(Collectors.toList());
		return listaRetorno;
	}


	@Override
	public ClienteDto findById(Integer idCliente) {
		Cliente prod = this.clienteRepositorio.findById(idCliente).get();
		if (prod == null) {
			return null;
		}
		return new ClienteDto(prod);
	}


	@Override
	public List<ClienteDto> findAllPorApellidoContains(String apellidos) {
		List<ClienteDto> listaRetorno = this.clienteRepositorio.findByPersonaApellidosContainingIgnoreCase(apellidos).stream().map(x -> {
			return new ClienteDto(x);
		}).collect(Collectors.toList());
		return listaRetorno;
	}

}
