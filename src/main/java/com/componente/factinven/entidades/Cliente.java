package com.componente.factinven.entidades;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
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
	
	private String nombreCompletos;

	@OneToMany(fetch = FetchType.LAZY,mappedBy="cliente")
	private List<Venta> listaComprobante;
	
	@OneToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="cliente")
	private List<ClienteRespuestas> listaClienteRepuestas;
	
	@OneToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="cliente")
	private List<OdontogramaRespuestas> listaOdontogramaRespuestas;

	@OneToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="cliente")
	private List<ConsultaCliente> listaConsultaCLiente;
	
	public String getnombreCompletos() {
		return persona.getApellidos()+" "+persona.getNombres();
	}
	
	public String getnombreCompletosFolder() {
		return persona.getApellidos()+"_"+persona.getNombres();
	}


	@ManyToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="client_files",
			joinColumns = {
					@JoinColumn(name="client_id")
			},
			inverseJoinColumns ={
					@JoinColumn(name="file_id")
			})
	private Set<ImageModel> clientFiles;
}
