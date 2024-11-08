package com.componente.factinven.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.componente.factinven.dto.ProductoDto;

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
public class Producto extends AuditedEntity implements Serializable {

	private static final long serialVersionUID = -7823071596492058940L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idProducto;
	private String nombre;
	private Double precioUnitario;
	private Double precioCompra;
	private Boolean servdeOdontograma;
	
	@OneToMany(mappedBy="producto")
	private List<DetalleVenta> detalleventaList;
	
    @JoinColumn(name = "idProveedor", referencedColumnName = "id", nullable = true)
    @ManyToOne(optional =true, fetch = FetchType.LAZY)
    private Proveedor proveedor;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy="tratamientosPieza")
	private List<OdontogramaRespuestas> tratamientosPieza;

    
	public Producto(ProductoDto productoRequest) {
	  this.idProducto=productoRequest.getIdProducto();
	  this.nombre= productoRequest.getNombre();
	  this.precioUnitario= productoRequest.getPrecioUnitario();
     }
    
	
//	public Producto() {
//		
//	}
	
//	public Producto(Integer idProducto, String nombre, Double precioUnitario, Integer stock, Double precioCompra,
//			Double precioVenta) {
//		this.idProducto = idProducto;
//		this.nombre = nombre;
//		this.precioUnitario = precioUnitario;
//		this.stock = stock;
//		this.precioCompra = precioCompra;
//		this.precioVenta = precioVenta;
//	}
//	
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}


	public Double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
//	public Double getPrecioVenta() {
//		return precioVenta;
//	}
//	public void setPrecioVenta(Double precioVenta) {
//		this.precioVenta = precioVenta;
//	}
}
