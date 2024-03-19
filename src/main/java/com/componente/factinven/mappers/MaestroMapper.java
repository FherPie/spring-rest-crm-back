package com.componente.factinven.mappers;

import com.componente.factinven.entidades.Maestro;
import com.componente.factinven.dto.MaestroDto;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaestroMapper extends EntityMapper<MaestroDto, Maestro> {

	@Override
	Maestro toEntity(MaestroDto dto);
	
	@Override
	MaestroDto toDto(Maestro entity);

	@Override
	List<Maestro> toEntity(List<MaestroDto> dtoList);

	@Override
	List<MaestroDto> toDto(List<Maestro> entityList);

	@Override
	Set<MaestroDto> toDto(Set<Maestro> entityList);
}
