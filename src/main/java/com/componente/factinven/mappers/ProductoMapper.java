package com.componente.factinven.mappers;


import com.componente.factinven.entidades.Producto;
import com.componente.factinven.dto.ProductoDto;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoMapper extends EntityMapper<ProductoDto, Producto> {

	@Override
	Producto toEntity(ProductoDto dto);

	@Override
	ProductoDto toDto(Producto entity);

	@Override
	List<Producto> toEntity(List<ProductoDto> dtoList);

	@Override
	List<ProductoDto> toDto(List<Producto> entityList);

	@Override
	Set<ProductoDto> toDto(Set<Producto> entityList);


}
