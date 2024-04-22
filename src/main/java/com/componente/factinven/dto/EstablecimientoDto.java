package com.componente.factinven.dto;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@JsonIgnoreProperties("imageEstablishment")
public class EstablecimientoDto  implements Serializable {

	private static final long serialVersionUID = 1028409979983251377L;
	private Integer id;
	private String nombre;
	private String direccion;
	private String ciudad;
	private String telefono;
	private String telefono2;
	private String codPostal;
	private String email;
	private String webSite;
	private String identificacion;
	private Set<FileHandle>  imageEstablishment;
}
