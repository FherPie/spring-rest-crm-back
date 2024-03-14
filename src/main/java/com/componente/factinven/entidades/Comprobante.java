package com.componente.factinven.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
@Inheritance(strategy= InheritanceType.JOINED)
public class Comprobante implements Serializable {

	private static final long serialVersionUID = 5422395194141514163L;

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
}
