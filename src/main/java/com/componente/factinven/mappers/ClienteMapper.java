package com.componente.factinven.mappers;


import com.componente.factinven.entidades.Cliente;
import com.componente.factinven.dto.ClienteResponse;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapping;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper extends EntityMapper<ClienteResponse, Cliente> {

	@Override
	Cliente toEntity(ClienteResponse dto);
	
	@Override
	@Mapping(target = "nombres", source = "persona.nombres")
	@Mapping(target = "apellidos", source = "persona.apellidos")
	@Mapping(target = "direccion", source = "persona.direccion")
	@Mapping(target = "telefono", source = "persona.telefono")
	@Mapping(target = "email", source = "persona.email")
	@Mapping(target = "nombresCompletos", source = "persona.apellidos")
	ClienteResponse toDto(Cliente entity);

	@Override
	List<Cliente> toEntity(List<ClienteResponse> dtoList);

	@Override
	List<ClienteResponse> toDto(List<Cliente> entityList);

	@Override
	Set<ClienteResponse> toDto(Set<Cliente> entityList);
}
