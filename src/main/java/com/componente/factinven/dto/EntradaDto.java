package com.componente.factinven.dto;

import lombok.Data;

@Data
public class EntradaDto {
	
	private int id;
	private Double precio;
	private String concepto;
	private ClienteDto clienteDto;
}
