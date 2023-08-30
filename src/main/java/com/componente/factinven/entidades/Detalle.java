package com.componente.factinven.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Detalle implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int id_maestro;
    private String nombre;
    private String parametros;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_maestro", insertable = false, updatable = false)
    private Maestro maestro;
}
