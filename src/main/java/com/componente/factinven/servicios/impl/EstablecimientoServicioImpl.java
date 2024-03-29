package com.componente.factinven.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.componente.factinven.dto.EstablecimientoDto;
import com.componente.factinven.entidades.Establecimiento;
import com.componente.factinven.entidades.Venta;
import com.componente.factinven.mappers.EstablecimientoMapper;
import com.componente.factinven.repositorios.EstablecimientoRepositorio;

@Service
@Transactional
public class EstablecimientoServicioImpl  {


	@Autowired
	EstablecimientoRepositorio establecimientoRespositorio;
	
	@Autowired
	private EstablecimientoMapper establecimientoMapper;

	public EstablecimientoDto guardar(EstablecimientoDto establecimientoDto) {
		Establecimiento establecimiento =establecimientoMapper.toEntity(establecimientoDto);
		return establecimientoMapper.toDto(establecimientoRespositorio.save(establecimiento));
	}



	public EstablecimientoDto actualizar(EstablecimientoDto establecimientoDto) {
		Establecimiento establecimiento =establecimientoMapper.toEntity(establecimientoDto);
		return establecimientoMapper.toDto(establecimientoRespositorio.save(establecimiento));
	}


	public List<EstablecimientoDto> findAll() {
		return establecimientoMapper.toDto(establecimientoRespositorio.findAll());
	}

	
	public EstablecimientoDto findById(Integer id) {
		return establecimientoMapper.toDto(establecimientoRespositorio.findById(id).get());
		//return new VentaResponse(comprobanteRespositorio.findById(id).get());
	}
	
	
	public boolean eliminar(Integer id) {
         establecimientoRespositorio.deleteById(id);
         return true;
	}

}
