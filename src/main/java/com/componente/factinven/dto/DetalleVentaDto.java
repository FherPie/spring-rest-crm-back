package com.componente.factinven.dto;

import com.componente.factinven.entidades.Articulo;
import com.componente.factinven.entidades.Producto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;


@Getter
@Setter
@Data
public class DetalleVentaDto implements Serializable
{
	private static final long serialVersionUID = -9023718735110319902L;

	private int id;
	private int unidad;
	private Double cantidad;
	private Articulo articulo;
	private BigDecimal precioUnitario;
	private BigDecimal precioporDetalle;
	private int numeroDetalle;

	private Producto producto;
}
