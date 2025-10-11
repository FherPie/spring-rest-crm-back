package com.componente.factinven.servicios.interfaz;

import java.util.List;

import com.componente.factinven.dto.DetalleVentaDto;

public interface IDetalleVentasServicio
{
    public abstract List<DetalleVentaDto> listarDetalles();
    
    public abstract List<DetalleVentaDto> listarDetallesVenta(Integer idVenta);
    
    public abstract Boolean algunDetalleConSaldo(Integer idVenta);
        
    public abstract List<DetalleVentaDto> formasPago();

    public abstract DetalleVentaDto findById(int id);

    public abstract DetalleVentaDto create(DetalleVentaDto detalle);

    public abstract DetalleVentaDto update(DetalleVentaDto detalle);

    public abstract void delete(int id);
}
