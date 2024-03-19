package com.componente.factinven.servicios.interfaz;

import com.componente.factinven.dto.DetalleDto;
import com.componente.factinven.dto.DetalleDto;

import java.util.List;

public interface IDetalleServicio
{
    public abstract List<DetalleDto> listarDetalles();

    public abstract DetalleDto findById(int id);

    public abstract DetalleDto create(DetalleDto detalle);

    public abstract DetalleDto update(DetalleDto detalle);

    public abstract void delete(int id);
}
