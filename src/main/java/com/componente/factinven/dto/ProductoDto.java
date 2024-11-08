package com.componente.factinven.dto;

import java.io.Serializable;

import com.componente.factinven.entidades.Producto;

public class ProductoDto implements Serializable {

	private static final long serialVersionUID = 1703147611246372118L;
	private Integer idProducto;
	private String nombre;
	private Double precioUnitario;
	private Double precioCompra;
	private Boolean servdeOdontograma;
		
	public ProductoDto() {

	}

	public  ProductoDto (Producto producto) {
		this.idProducto= producto.getIdProducto();
		this.nombre= producto.getNombre();
		this.precioUnitario= producto.getPrecioUnitario();
	}
	
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public Boolean getServdeOdontograma() {
		return servdeOdontograma;
	}

	public void setServdeOdontograma(Boolean servdeOdontograma) {
		this.servdeOdontograma = servdeOdontograma;
	}
}
