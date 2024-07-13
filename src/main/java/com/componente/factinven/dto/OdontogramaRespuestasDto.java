package com.componente.factinven.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OdontogramaRespuestasDto {

	private Integer id;
	private String descripcion;
	private Integer pieza;
	private String nombre;
	private String odontogramaPieza;
	//private ClienteDto cliente;
}
