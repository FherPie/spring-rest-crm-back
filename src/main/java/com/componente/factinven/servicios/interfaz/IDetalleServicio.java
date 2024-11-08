package com.componente.factinven.servicios.interfaz;

import java.util.List;

import com.componente.factinven.dto.DetalleDto;

public interface IDetalleServicio
{
    public abstract List<DetalleDto> listarDetalles();
    
    public abstract List<DetalleDto> formasPago();

    public abstract DetalleDto findById(int id);

    public abstract DetalleDto create(DetalleDto detalle);

    public abstract DetalleDto update(DetalleDto detalle);

    public abstract void delete(int id);
}
