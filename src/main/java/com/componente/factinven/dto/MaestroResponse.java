package com.componente.factinven.dto;

import com.componente.factinven.entidades.Maestro;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MaestroResponse implements Serializable
{
    private int id;
    private String nombre;
    private String descripcion;

    public MaestroResponse(Maestro maestro) {
    	this.id = maestro.getId();
    	this.nombre = maestro.getNombre();
    	this.descripcion= maestro.getDescripcion();
    }
}
