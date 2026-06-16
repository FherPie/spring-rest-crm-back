package com.componente.factinven.entidades;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="mensajes_whatsapp")
public class MensajesWhatsapp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime fechayHora;
    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente cliente;
    @ManyToOne(fetch = FetchType.EAGER)
    private Mascotas mascota;
    private String motivoConsulta;
    @ManyToOne(fetch = FetchType.EAGER)
    private Producto producto;
    @ManyToOne(fetch = FetchType.EAGER)
    private Establecimiento establecimiento;
}
