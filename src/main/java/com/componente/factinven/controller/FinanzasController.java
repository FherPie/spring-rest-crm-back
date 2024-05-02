package com.componente.factinven.controller;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.componente.factinven.entidades.Entrada;
import com.componente.factinven.entidades.Salida;
import com.componente.factinven.repositorios.EntradasRespository;
import com.componente.factinven.repositorios.SalidasRespository;
import com.componente.factinven.servicios.impl.FinanzasImpl;

@RestController
@RequestMapping("/api")
public class FinanzasController {
	
	EntradasRespository entradasR;
	SalidasRespository salidasR;
	FinanzasImpl finanzasImpl;
	
	
	public FinanzasController(EntradasRespository entradasR,SalidasRespository salidasR, FinanzasImpl finanzasImpl ) {
		this.entradasR=entradasR;
		this.salidasR=salidasR;
		this.finanzasImpl= finanzasImpl;
	}
	

	@PostMapping("entrada")
	public Entrada guardarEntrada(@RequestBody Entrada entradaBody) {
		entradaBody.setCreatedDate(new Date());
		var entrada= entradasR.save(entradaBody);
		return entrada;
	}
	
	@PutMapping("entrada")
	public Entrada actualizarEntrada(@RequestBody Entrada entradaBody) {
		entradaBody.setUpdatedDate(new Date());
		var entrada= entradasR.save(entradaBody);
		return entrada;
	}
	
	@GetMapping("entrada/{entradaId}")
	public Entrada obtenerEntrada(@PathVariable int entradaId) {
		var entrada= entradasR.findById(entradaId).get();
		return entrada;
	}
	
	@DeleteMapping("entrada/{entradaId}")
	public void borrarEntrada(@PathVariable int entradaId) {
		entradasR.deleteById(entradaId);
	}
	
	
	@GetMapping("entradas")
	public List<Entrada> obtenerEntradas() {
		var entradas= entradasR.findAll();
		return entradas;
	}
	
	
	@PostMapping("salida")
	public Salida guardarSalida(@RequestBody Salida salidaBody) {
		salidaBody.setCreatedDate(new Date());
		var salida= salidasR.save(salidaBody);
		return salida;
	}
	
	@PutMapping("salida")
	public Salida actualizarSalida(@RequestBody Salida salidaBody) {
		salidaBody.setUpdatedDate(new Date());
		var salida= salidasR.save(salidaBody);
		return salida;
	}
	
	@GetMapping("salida/{salidaId}")
	public Salida obtenerSalida(@PathVariable int salidaId) {
		var salida= salidasR.findById(salidaId).get();
		return salida;
	}
	
	@DeleteMapping("salida/{salidaId}")
	public void borrarSalida(@PathVariable int salidaId) {
		salidasR.deleteById(salidaId);
	}
	
	
	@GetMapping("salidas")
	public List<Salida> obtenerSalidas() {
		var salidas= salidasR.findAll();
		return salidas;
	}	
	

	@GetMapping("resultados")
	public FinanzasImpl.ResultadosDto resultados() {
		var resultados= finanzasImpl.resultados();
		return resultados;
	}
	

	
}
