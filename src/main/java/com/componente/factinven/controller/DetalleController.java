package com.componente.factinven.controller;

import com.componente.factinven.dto.DetalleDto;
import com.componente.factinven.dto.DetalleDto;
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
    public List<DetalleDto> listar()
    {
        return detalleServicio.listarDetalles();
    }

    @GetMapping("/detalle/{id}")
    public DetalleDto findById(@PathVariable int id)
    {
        return detalleServicio.findById(id);
    }

    @PostMapping("/detalle")
    public DetalleDto create(@RequestBody DetalleDto detalleResponse)
    {
        return detalleServicio.create(detalleResponse);
    }

    @PutMapping("/detalle")
    public DetalleDto update(@RequestBody DetalleDto detalleResponse)
    {
        return detalleServicio.update(detalleResponse);
    }

    @DeleteMapping("/detalle/{id}")
    public void delete(@PathVariable int id)
    {
        detalleServicio.delete(id);
    }
    
    @GetMapping("/detalle/formasPago")
    public List<DetalleDto>  formasPago()
    {
        return detalleServicio.formasPago();
    }
}
