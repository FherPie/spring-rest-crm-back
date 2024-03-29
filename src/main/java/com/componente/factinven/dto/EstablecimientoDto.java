package com.componente.factinven.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
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
}
