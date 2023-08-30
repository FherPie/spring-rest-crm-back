package com.componente.factinven.servicios.interfaz;

import com.componente.factinven.dto.DetalleRequest;
import com.componente.factinven.dto.DetalleResponse;

import java.util.List;

public interface IDetalleServicio
{
    public abstract List<DetalleResponse> listarDetalles();

    public abstract DetalleResponse findById(int id);

    public abstract DetalleResponse create(DetalleRequest detalle);

    public abstract DetalleResponse update(DetalleRequest detalle);

    public abstract void delete(int id);
}
