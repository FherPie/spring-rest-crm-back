package com.componente.factinven.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import com.componente.factinven.entidades.Detalle;
import com.componente.factinven.entidades.Maestro;

@Getter
@Setter
public class DetalleDto implements Serializable
{
	private int id;
    private int id_maestro;
    private String nombre;
    private String parametros;
    private String descripcion;
    private Maestro maestro;
}
