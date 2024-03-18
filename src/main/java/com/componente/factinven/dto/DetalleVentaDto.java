package com.componente.factinven.dto;

import com.componente.factinven.entidades.Articulo;
import com.componente.factinven.entidades.Producto;
import com.componente.factinven.entidades.Venta;

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
	private Double cantidad=0.0;
	private Double precioUnitario=0.0;
	private Double precioporDetalle=0.0;
	private int descuentoUnitario=0;
	private int numeroDetalle;
	private boolean isEdit;
	private VentaResponse venta;


	private ProductoDto productoDto;
}
