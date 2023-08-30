package com.componente.factinven.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ClienteRequest implements Serializable {

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
    private String categoria;
    private Integer idPersona;
}
