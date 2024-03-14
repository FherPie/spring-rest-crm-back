package com.componente.factinven.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Venta  implements Serializable {
	
	
	private static final long serialVersionUID1 = 5422395194141514163L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotNull
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime fechayHora;
	private Empleado empleado;
	@ManyToOne(fetch = FetchType.EAGER)
	private Cliente cliente;
	@NotBlank
	private String estado;  //Borrador, Cerrado
	@NotBlank
	private String codigo;
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Almacen almacen;
	@NotEmpty
	private String formaPago;
	private BigDecimal total;
	//@NotEmpty
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="venta", orphanRemoval = true)
	private List<DetalleVenta> detallesVenta;

}
