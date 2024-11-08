package com.componente.factinven.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EntradaDto {
	
	private int id;
	private Double precio;
	private String concepto;
	private VentaResponse idVenta;
	private Date createdDate;
	private Date updatedDate;
	private String nombreCliente;
	private String nombreProducto;

	private DetalleVentaDto idDetalle;
}
