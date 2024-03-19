package com.componente.factinven.servicios.interfaz;

import com.componente.factinven.dto.MaestroDto;
import com.componente.factinven.dto.MaestroDto;
import com.componente.factinven.entidades.Maestro;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IMaestroServicio
{
    public abstract List<MaestroDto> listarMaestros();

    public abstract MaestroDto findById(int id);

    public abstract MaestroDto create(MaestroDto maestro);

    public abstract MaestroDto update(MaestroDto maestro);

    public abstract void delete(int id);
}
