package com.componente.factinven.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MaestroRequest  implements Serializable
{
    private int id;
    private String nombre;
    private String descripcion;
}
