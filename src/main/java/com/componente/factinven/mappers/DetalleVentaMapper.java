package com.componente.factinven.mappers;


import com.componente.factinven.entidades.DetalleVenta;
import com.componente.factinven.entidades.Venta;
import com.componente.factinven.dto.DetalleVentaDto;
import com.componente.factinven.dto.VentaResponse;

import java.util.List;
import java.util.Set;
import org.mapstruct.Mapping;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DetalleVentaMapper extends EntityMapper<DetalleVentaDto, DetalleVenta> {

	@Override
	DetalleVenta toEntity(DetalleVentaDto dto);

	@Override
	DetalleVentaDto toDto(DetalleVenta entity);

	@Override
	List<DetalleVenta> toEntity(List<DetalleVentaDto> dtoList);

	@Override
	List<DetalleVentaDto> toDto(List<DetalleVenta> entityList);

	@Override
	Set<DetalleVentaDto> toDto(Set<DetalleVenta> entityList);


}
