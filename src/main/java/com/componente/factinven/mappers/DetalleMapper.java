package com.componente.factinven.mappers;

import com.componente.factinven.entidades.Detalle;
import com.componente.factinven.dto.DetalleDto;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DetalleMapper extends EntityMapper<DetalleDto, Detalle> {

	@Override
	Detalle toEntity(DetalleDto dto);
	
	@Override
	DetalleDto toDto(Detalle entity);

	@Override
	List<Detalle> toEntity(List<DetalleDto> dtoList);

	@Override
	List<DetalleDto> toDto(List<Detalle> entityList);

	@Override
	Set<DetalleDto> toDto(Set<Detalle> entityList);
}
