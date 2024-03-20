package com.componente.factinven.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1869080843648181632L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	@NotNull
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Persona persona;
	
	
	private String categoria;
	
	private String motivoConsulta;
	
	private String referidoPor;
	
	private String ocupacion;

	@OneToMany(fetch = FetchType.LAZY,mappedBy="cliente")
	private List<Venta> listaComprobante;
	
	@OneToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="cliente")
	private List<ClienteRespuestas> listaClienteRepuestas;

}
