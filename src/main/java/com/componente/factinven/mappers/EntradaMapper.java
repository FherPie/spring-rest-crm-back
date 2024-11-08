package com.componente.factinven.mappers;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.componente.factinven.dto.EntradaDto;
import com.componente.factinven.entidades.Entrada;

@Mapper(componentModel= "spring")
public interface EntradaMapper extends EntityMapper<EntradaDto, Entrada>  {

	@Override
	@Mapping(target="venta", source="idVenta")
	@Mapping(target="detalleVenta", source="idDetalle")
	public Entrada toEntity(EntradaDto dto);
	
	@Override
	@Mapping(target="idVenta", source="venta")
	@Mapping(target="idDetalle", source="detalleVenta")
	public EntradaDto toDto(Entrada entity);
	@Override
	public List<Entrada> toEntity(List<EntradaDto> dtoList);

	@Override
	public List<EntradaDto> toDto(List<Entrada> entityList);

	@Override
	public Set<EntradaDto> toDto(Set<Entrada> entityList);

}
