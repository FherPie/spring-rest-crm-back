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
	@Mapping(target = "persona.nombres", source = "nombres")
	@Mapping(target = "persona.apellidos", source = "apellidos")
	@Mapping(target = "persona.direccion", source = "direccion")
	@Mapping(target = "persona.telefono", source = "telefono")
	@Mapping(target = "persona.telefono2", source = "telefono2")
	@Mapping(target = "persona.email", source = "email")
	@Mapping(target = "persona.identificacion", source = "identificacion")
	@Mapping(target = "persona.id", source = "idPersona")
	@Mapping(target = "persona.fechaNacimiento", source = "fechaNacimiento")
	@Mapping(target = "listaClienteRepuestas", source="listaClienteRespuestasDto")
	Cliente toEntity(ClienteDto dto);
	
	@Override
	@Mapping(target = "nombres", source = "persona.nombres")
	@Mapping(target = "apellidos", source = "persona.apellidos")
	@Mapping(target = "direccion", source = "persona.direccion")
	@Mapping(target = "telefono", source = "persona.telefono")
	@Mapping(target = "telefono2", source = "persona.telefono2")
	@Mapping(target = "email", source = "persona.email")
	@Mapping(target = "identificacion", source = "persona.identificacion")
	@Mapping(target = "nombresCompletos", source = "persona.apellidos")
	@Mapping(target = "idPersona", source = "persona.id")
	@Mapping(target = "fechaNacimiento", source = "persona.fechaNacimiento")
	@Mapping(target="listaClienteRespuestasDto", source="listaClienteRepuestas")
	ClienteDto toDto(Cliente entity);

	@Override
	List<Cliente> toEntity(List<ClienteDto> dtoList);

	@Override
	List<ClienteDto> toDto(List<Cliente> entityList);

	@Override
	Set<ClienteDto> toDto(Set<Cliente> entityList);
}
