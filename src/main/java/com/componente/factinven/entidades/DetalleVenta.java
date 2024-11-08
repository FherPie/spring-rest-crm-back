package com.componente.factinven.entidades;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DetalleVenta {

	
	private static final long serialVersionUID = -9023718735110319902L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Double cantidad;
//	@ManyToOne(fetch = FetchType.LAZY)
//	private Articulo articulo;
	private Double precioUnitario;
	private BigDecimal precioporDetalle;
	private Double descuentoUnitario;
	private int numeroDetalle;
	
	@Transient
	private Double saldo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Producto producto;
	
	@NotNull	
	@ManyToOne(fetch = FetchType.LAZY)
	private Venta venta;


}
