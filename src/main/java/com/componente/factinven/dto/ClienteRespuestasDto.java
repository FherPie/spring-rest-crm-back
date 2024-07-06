package com.componente.factinven.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import com.componente.factinven.entidades.Detalle;
import com.componente.factinven.entidades.Maestro;

@Getter
@Setter
@AllArgsConstructor
public class ClienteRespuestasDto implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int id;
	private String respuestText;
	private Integer respuestInteger;
	private Boolean respuestBoolean;
	private Double respuestDouble;
	private DetalleDto pregunta;
	//private ClienteDto cliente;
}
