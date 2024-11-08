package com.componente.factinven.mappers;


import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.componente.factinven.dto.DetalleVentaDto;
import com.componente.factinven.entidades.DetalleVenta;

@Mapper(componentModel = "spring")
public interface DetalleVentaMapper extends EntityMapper<DetalleVentaDto, DetalleVenta> {

	@Override
	@Mapping(target = "producto", source = "productoDto")
	DetalleVenta toEntity(DetalleVentaDto dto);

	@Override
	@Mapping(target = "productoDto", source = "producto")
	DetalleVentaDto toDto(DetalleVenta entity);

	@Override
	List<DetalleVenta> toEntity(List<DetalleVentaDto> dtoList);

	@Override
	List<DetalleVentaDto> toDto(List<DetalleVenta> entityList);

	@Override
	Set<DetalleVentaDto> toDto(Set<DetalleVenta> entityList);


}
