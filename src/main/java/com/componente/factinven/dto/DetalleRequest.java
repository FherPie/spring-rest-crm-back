package com.componente.factinven.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DetalleRequest implements Serializable
{
    private int id;
    private int id_maestro;
    private String nombre;
    private String parametros;
    private String descripcion;
}
