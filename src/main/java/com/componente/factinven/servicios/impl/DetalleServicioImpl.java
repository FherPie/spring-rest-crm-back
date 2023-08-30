package com.componente.factinven.servicios.impl;

import com.componente.factinven.dto.DetalleRequest;
import com.componente.factinven.dto.DetalleResponse;
import com.componente.factinven.entidades.Detalle;
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

    @Override
    public List<DetalleResponse> listarDetalles() {

        List<DetalleResponse> data = this.detalleRepositorio.findAll().stream().map(DetalleResponse::new).collect(java.util.stream.Collectors.toList());

        return data;
    }

    @Override
    public DetalleResponse findById(int id)
    {
        Detalle detalle = this.detalleRepositorio.findById(id).get();

        return new DetalleResponse(detalle);
    }

    @Override
    public DetalleResponse create(DetalleRequest detalle)
    {
        Detalle detalleGuardar = new Detalle();

        detalleGuardar.setId_maestro(detalle.getId_maestro());
        detalleGuardar.setNombre(detalle.getNombre());
        detalleGuardar.setParametros(detalle.getParametros());
        detalleGuardar.setDescripcion(detalle.getDescripcion());

        return new DetalleResponse(this.detalleRepositorio.save(detalleGuardar));
    }

    @Override
    public DetalleResponse update(DetalleRequest detalle)
    {
        Detalle detalleGuardar = this.detalleRepositorio.findById(detalle.getId()).get();

        detalleGuardar.setId(detalle.getId());
        detalleGuardar.setId_maestro(detalle.getId_maestro());
        detalleGuardar.setNombre(detalle.getNombre());
        detalleGuardar.setParametros(detalle.getParametros());
        detalleGuardar.setDescripcion(detalle.getDescripcion());

        return new DetalleResponse(this.detalleRepositorio.save(detalleGuardar));
    }

    @Override
    public void delete(int id)
    {
        this.detalleRepositorio.deleteById(id);
    }
}
