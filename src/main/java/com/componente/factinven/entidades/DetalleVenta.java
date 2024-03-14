package com.componente.factinven.entidades;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class DetalleVenta {

	
	private static final long serialVersionUID = -9023718735110319902L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotNull
	private int unidad;
	@NotNull
	private Double cantidad;
	@ManyToOne(fetch = FetchType.LAZY)
	private Articulo articulo;
	@NotNull
	private BigDecimal precioUnitario;
	private BigDecimal precioporDetalle;
	@NotNull
	private int numeroDetalle;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Producto producto;
	
	@NotNull	
	@ManyToOne(fetch = FetchType.LAZY)
	private Venta venta;


}
