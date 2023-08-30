package com.componente.factinven.controller;

import com.componente.factinven.dto.MaestroRequest;
import com.componente.factinven.dto.MaestroResponse;
import com.componente.factinven.servicios.impl.MaestroServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class MaestroController
{
    @Autowired
    MaestroServicioImpl maestroServicio;

    @GetMapping("/maestro")
    public List<MaestroResponse> listar()
    {
        return maestroServicio.listarMaestros();
    }

    @GetMapping("/maestro/{id}")
    public MaestroResponse findById(@PathVariable int id)
    {
        return maestroServicio.findById(id);
    }

    @PostMapping("/maestro")
    public MaestroResponse create(@RequestBody MaestroRequest maestroRequest)
    {
        return maestroServicio.create(maestroRequest);
    }

    @PutMapping("/maestro")
    public MaestroResponse update(@RequestBody MaestroRequest maestroRequest)
    {
        return maestroServicio.update(maestroRequest);
    }

    @DeleteMapping("/maestro/{id}")
    public void delete(@PathVariable int id)
    {
        maestroServicio.delete(id);
    }

}
