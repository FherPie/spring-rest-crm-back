package com.componente.factinven.entidades;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class Entrada extends AuditedEntity  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotNull
	private Double precio;
    private String concepto;
	@ManyToOne(fetch = FetchType.LAZY)
    private Venta venta;
	@ManyToOne(fetch = FetchType.LAZY)
    private DetalleVenta detalleVenta;
	
}
