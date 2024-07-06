package com.componente.factinven.mappers;


import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;

import com.componente.factinven.dto.ClienteRespuestasDto;
import com.componente.factinven.entidades.ClienteRespuestas;

@Mapper(componentModel = "spring")
public interface ClienteRespuestasMapper extends EntityMapper<ClienteRespuestasDto, ClienteRespuestas> {

	@Override
//	@Mapping(target = "producto", source = "productoDto")
	ClienteRespuestas toEntity(ClienteRespuestasDto dto);

	@Override
//	@Mapping(target = "productoDto", source = "producto")
	ClienteRespuestasDto toDto(ClienteRespuestas entity);

	@Override
	List<ClienteRespuestas> toEntity(List<ClienteRespuestasDto> dtoList);

	@Override
	List<ClienteRespuestasDto> toDto(List<ClienteRespuestas> entityList);

	@Override
	Set<ClienteRespuestasDto> toDto(Set<ClienteRespuestas> entityList);


}
