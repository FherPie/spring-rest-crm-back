package com.componente.factinven.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

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
	
	//azules prestaciones requeridas
	private Boolean machaBlanca;
	private Boolean fractura;
	private Boolean caries;
	private Boolean indicadoExtracciones;
	
	
	//rojas prestaciones existentes
	
	private Boolean obturacionAmalgama;
	private Boolean corona;
	private Boolean selladoresFosa;
	private Boolean obturacionResina;

	private Set<FileHandle> trtatamientosPiezaDto;
	
	
	
	
	//private ClienteDto cliente;
}
