package com.componente.factinven.mappers;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;

import com.componente.factinven.dto.OdontogramaRespuestasDto;
import com.componente.factinven.entidades.OdontogramaRespuestas;

@Mapper(componentModel="spring")
public interface OdontogramaRespuestasMapper extends EntityMapper<OdontogramaRespuestasDto, OdontogramaRespuestas> {


	
	@Override
	OdontogramaRespuestas toEntity(OdontogramaRespuestasDto dto);
	
	@Override
	OdontogramaRespuestasDto toDto(OdontogramaRespuestas entity);

	@Override
	List<OdontogramaRespuestas> toEntity(List<OdontogramaRespuestasDto> dtoList);
	
	@Override
	List<OdontogramaRespuestasDto> toDto(List<OdontogramaRespuestas> entityList);
	
	@Override
	Set<OdontogramaRespuestasDto> toDto(Set<OdontogramaRespuestas> enityList);
	
	
	
	
	
}
