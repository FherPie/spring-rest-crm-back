package com.componente.factinven.dto;

import com.componente.factinven.entidades.Detalle;
import com.componente.factinven.entidades.Maestro;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DetalleResponse implements Serializable
{
    private int id;
    private int id_maestro;
    private String nombre;
    private String parametros;
    private String descripcion;

    private Maestro maestro;

    public DetalleResponse(Detalle detalle) {
    	this.id = detalle.getId();
    	this.id_maestro = detalle.getId_maestro();
    	this.nombre = detalle.getNombre();
    	this.parametros = detalle.getParametros();
    	this.descripcion = detalle.getDescripcion();

        this.maestro = detalle.getMaestro();
    }
}
