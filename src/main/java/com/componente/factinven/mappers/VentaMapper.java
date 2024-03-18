package com.componente.factinven.mappers;


import com.componente.factinven.entidades.Venta;
import com.componente.factinven.dto.VentaResponse;

import java.util.List;
import java.util.Set;
import org.mapstruct.Mapping;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {ClienteMapper.class, DetalleVentaMapper.class})
public interface VentaMapper extends EntityMapper<VentaResponse, Venta> {

	@Override
	@Mapping(target = "cliente", source = "idCliente")
	@Mapping(target = "detallesVenta", source = "detallesVentaDto")
	Venta toEntity(VentaResponse dto);

	@Override
	@Mapping(target = "idCliente", source = "cliente")
	@Mapping(target = "detallesVentaDto", source = "detallesVenta")
	VentaResponse toDto(Venta entity);

	@Override
	List<Venta> toEntity(List<VentaResponse> dtoList);

	@Override
	List<VentaResponse> toDto(List<Venta> entityList);

	@Override
	Set<VentaResponse> toDto(Set<Venta> entityList);


}
