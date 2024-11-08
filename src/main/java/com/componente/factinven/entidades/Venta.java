package com.componente.factinven.entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name="venta")
public class Venta   {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime fechayHora;
	private Empleado empleado;
	@ManyToOne(fetch = FetchType.EAGER)
	private Cliente cliente;
	private String estado;  //1 proforma 2 finalizado
	private String codigo;
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Almacen almacen;
	private String formaPago;
	private Double total;
	private Double totalSinDescuento;
	private Double totalDescuento;
	private String motivoConsulta;
	//@NotEmpty
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="venta", orphanRemoval = true)
	private List<DetalleVenta> detallesVenta= new ArrayList<>();
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="venta", orphanRemoval = false)
	private List<Entrada> pagos= new ArrayList<>();
	
	
	public void addDetail(DetalleVenta detalleVenta) {
		this.detallesVenta.add(detalleVenta);
		detalleVenta.setVenta(this);
	}
	
	public void removeDetail(DetalleVenta detalleVenta) {
		this.detallesVenta.remove(detalleVenta);
		detalleVenta.setVenta(null);
	}
	
	public void removeDetails() {
		
		Iterator<DetalleVenta> iterator= this.detallesVenta.iterator();
		
		while(iterator.hasNext()) {
			DetalleVenta detalleVenta= iterator.next();
			detalleVenta.setVenta(null);
			iterator.remove();
		}
	}
	
	


}
