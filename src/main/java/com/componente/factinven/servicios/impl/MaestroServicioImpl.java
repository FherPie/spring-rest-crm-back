package com.componente.factinven.servicios.impl;

import com.componente.factinven.dto.MaestroRequest;
import com.componente.factinven.dto.MaestroResponse;
import com.componente.factinven.entidades.Maestro;
import com.componente.factinven.repositorios.MaestroRepositorio;
import com.componente.factinven.servicios.interfaz.IMaestroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaestroServicioImpl implements IMaestroServicio
{
    @Autowired
    private MaestroRepositorio maestroRepositorio;

    @Override
    public List<MaestroResponse> listarMaestros()
    {
        List<MaestroResponse> data = this.maestroRepositorio.findAll().stream().map(MaestroResponse::new).collect(java.util.stream.Collectors.toList());

        return data;
    }

    @Override
    public MaestroResponse findById(int id)
    {
        Maestro maestro = this.maestroRepositorio.findById(id).get();

        return new MaestroResponse(maestro);
    }

    @Override
    public MaestroResponse create(MaestroRequest maestro)
    {
        Maestro maestroGuardar = new Maestro();

        maestroGuardar.setNombre(maestro.getNombre());
        maestroGuardar.setDescripcion(maestro.getDescripcion());

        return new MaestroResponse(this.maestroRepositorio.save(maestroGuardar));
    }

    @Override
    public MaestroResponse update(MaestroRequest maestro)
    {
        Maestro maestroGuardar = new Maestro();

        maestroGuardar.setId(maestro.getId());
        maestroGuardar.setNombre(maestro.getNombre());
        maestroGuardar.setDescripcion(maestro.getDescripcion());


        return new MaestroResponse(this.maestroRepositorio.save(maestroGuardar));
    }

    @Override
    public void delete(int id)
    {
        this.maestroRepositorio.deleteById(id);
    }
}
