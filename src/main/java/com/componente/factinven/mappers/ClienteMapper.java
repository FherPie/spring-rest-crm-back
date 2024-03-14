package com.componente.factinven.mappers;


import com.componente.factinven.entidades.Cliente;
import com.componente.factinven.dto.ClienteDto;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapping;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper extends EntityMapper<ClienteDto, Cliente> {

	@Override
	Cliente toEntity(ClienteDto dto);
	
	@Override
	@Mapping(target = "nombres", source = "persona.nombres")
	@Mapping(target = "apellidos", source = "persona.apellidos")
	@Mapping(target = "direccion", source = "persona.direccion")
	@Mapping(target = "telefono", source = "persona.telefono")
	@Mapping(target = "email", source = "persona.email")
	@Mapping(target = "nombresCompletos", source = "persona.apellidos")
	ClienteDto toDto(Cliente entity);

	@Override
	List<Cliente> toEntity(List<ClienteDto> dtoList);

	@Override
	List<ClienteDto> toDto(List<Cliente> entityList);

	@Override
	Set<ClienteDto> toDto(Set<Cliente> entityList);
}
