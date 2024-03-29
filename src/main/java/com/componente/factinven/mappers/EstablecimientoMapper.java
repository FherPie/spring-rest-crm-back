package com.componente.factinven.mappers;


import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;

import com.componente.factinven.dto.EstablecimientoDto;
import com.componente.factinven.entidades.Establecimiento;

@Mapper(componentModel = "spring")
public interface EstablecimientoMapper extends EntityMapper<EstablecimientoDto, Establecimiento> {

	@Override
	Establecimiento toEntity(EstablecimientoDto dto);

	@Override
	EstablecimientoDto toDto(Establecimiento entity);

	@Override
	List<Establecimiento> toEntity(List<EstablecimientoDto> dtoList);

	@Override
	List<EstablecimientoDto> toDto(List<Establecimiento> entityList);

	@Override
	Set<EstablecimientoDto> toDto(Set<Establecimiento> entityList);


}
