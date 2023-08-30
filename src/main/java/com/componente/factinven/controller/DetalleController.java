package com.componente.factinven.controller;

import com.componente.factinven.dto.DetalleRequest;
import com.componente.factinven.dto.DetalleResponse;
import com.componente.factinven.servicios.impl.DetalleServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class DetalleController
{
    @Autowired
    DetalleServicioImpl detalleServicio;

    @GetMapping("/detalle")
    public List<DetalleResponse> listar()
    {
        return detalleServicio.listarDetalles();
    }

    @GetMapping("/detalle/{id}")
    public DetalleResponse findById(@PathVariable int id)
    {
        return detalleServicio.findById(id);
    }

    @PostMapping("/detalle")
    public DetalleResponse create(@RequestBody DetalleRequest detalleResponse)
    {
        return detalleServicio.create(detalleResponse);
    }

    @PutMapping("/detalle")
    public DetalleResponse update(@RequestBody DetalleRequest detalleResponse)
    {
        return detalleServicio.update(detalleResponse);
    }

    @DeleteMapping("/detalle/{id}")
    public void delete(@PathVariable int id)
    {
        detalleServicio.delete(id);
    }
}
