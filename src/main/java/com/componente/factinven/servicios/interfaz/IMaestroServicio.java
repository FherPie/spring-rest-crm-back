package com.componente.factinven.servicios.interfaz;

import com.componente.factinven.dto.MaestroRequest;
import com.componente.factinven.dto.MaestroResponse;
import com.componente.factinven.entidades.Maestro;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IMaestroServicio
{
    public abstract List<MaestroResponse> listarMaestros();

    public abstract MaestroResponse findById(int id);

    public abstract MaestroResponse create(MaestroRequest maestro);

    public abstract MaestroResponse update(MaestroRequest maestro);

    public abstract void delete(int id);
}
