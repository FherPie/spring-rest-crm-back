package com.componente.factinven.servicios.impl;

import com.componente.factinven.dto.DetalleDto;
import com.componente.factinven.entidades.Detalle;
import com.componente.factinven.mappers.DetalleMapper;
import com.componente.factinven.repositorios.DetalleRepositorio;
import com.componente.factinven.servicios.interfaz.IDetalleServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleServicioImpl implements IDetalleServicio
{
    @Autowired
    private DetalleRepositorio detalleRepositorio;
    
    
    @Autowired
    private DetalleMapper detalleMapper;

    @Override
    public List<DetalleDto> listarDetalles() {

        List<DetalleDto> data = detalleMapper.toDto(this.detalleRepositorio.findAll()); 
        return data;
    }

    @Override
    public DetalleDto findById(int id)
    {
        Detalle detalle = this.detalleRepositorio.findById(id).get();

        return  detalleMapper.toDto(detalle);
    }

    @Override
    public DetalleDto create(DetalleDto detalle)
    {
        Detalle detalleGuardar = detalleMapper.toEntity(detalle);

        return detalleMapper.toDto(this.detalleRepositorio.save(detalleGuardar));
    }

    @Override
    public DetalleDto update(DetalleDto detalle)
    {
        Detalle detalleGuardar = detalleMapper.toEntity(detalle);

        return detalleMapper.toDto(this.detalleRepositorio.save(detalleGuardar));
    }

    @Override
    public void delete(int id)
    {
        this.detalleRepositorio.deleteById(id);
    }
}
