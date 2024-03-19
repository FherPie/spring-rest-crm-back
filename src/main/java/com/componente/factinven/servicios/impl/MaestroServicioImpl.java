package com.componente.factinven.servicios.impl;

import com.componente.factinven.dto.MaestroDto;
import com.componente.factinven.entidades.Maestro;
import com.componente.factinven.mappers.MaestroMapper;
import com.componente.factinven.repositorios.MaestroRepositorio;
import com.componente.factinven.servicios.interfaz.IMaestroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaestroServicioImpl implements IMaestroServicio
{
    @Autowired
    private MaestroRepositorio maestroRepositorio;
    
    
    @Autowired
    private MaestroMapper maestroMapper;

    @Override
    public List<MaestroDto> listarMaestros()
    {
        List<MaestroDto> data = maestroMapper.toDto(this.maestroRepositorio.findAll());
        return data;
    }

    @Override
    public MaestroDto findById(int id)
    {
        Maestro maestro = this.maestroRepositorio.findById(id).get();

        return maestroMapper.toDto(maestro);
    }

    @Override
    public MaestroDto create(MaestroDto maestro)
    {
        Maestro maestroGuardar = maestroMapper.toEntity(maestro);
        return maestroMapper.toDto(this.maestroRepositorio.save(maestroGuardar)); 
    }

    @Override
    public MaestroDto update(MaestroDto maestro)
    {
        Maestro maestroGuardar = maestroMapper.toEntity(maestro);
        return maestroMapper.toDto(this.maestroRepositorio.save(maestroGuardar)); 
    }

    @Override
    public void delete(int id)
    {
        this.maestroRepositorio.deleteById(id);
    }
}
