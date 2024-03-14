package com.componente.factinven.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.componente.factinven.entidades.Cliente;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ClienteDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nombres;
	private String apellidos;
	private String direccion;
    private String telefono;
    private String email;
	public LocalDate fechaNacimiento;
    private String nombreUsuario;
    private String pin;
    private String identificacion;
    private Integer idPersona;
    private Integer id;
	private String nombresCompletos;
    
    
    public ClienteDto(Cliente cliente) {	    
    	this.nombres=cliente.getPersona().getNombres();
    	this.apellidos=cliente.getPersona().getApellidos();
    	this.direccion=cliente.getPersona().getDireccion();
    	this.telefono=cliente.getPersona().getTelefono();
    	this.email=cliente.getPersona().getEmail();
    	this.email=cliente.getPersona().getEmail();
    	this.identificacion=cliente.getPersona().getIdentificacion();
    	this.idPersona=cliente.getPersona().getId();
    	this.id=cliente.getId();
		this.nombresCompletos = this.apellidos+ " " +this.nombres;
	}


	public ClienteDto() {
		// TODO Auto-generated constructor stub
	}

}
