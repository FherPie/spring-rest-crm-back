package com.componente.factinven.entidades;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
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
public class ClienteRespuestas implements Serializable {

	private static final long serialVersionUID = 1869080843648181632L;
	@Id
	private int id;
	private String respuestText;
	private Integer respuestInteger;
	private Boolean respuestBoolean;
	private Double respuestDouble;
	
	@MapsId()
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pregunta_id")
	private Detalle pregunta;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="client_id")
	private Cliente cliente;
}
