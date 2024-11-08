package com.componente.factinven.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.componente.factinven.dto.EntradaDto;
import com.componente.factinven.entidades.Entrada;
import com.componente.factinven.entidades.Salida;
import com.componente.factinven.entidades.Venta;
import com.componente.factinven.mappers.EntradaMapper;
import com.componente.factinven.repositorios.EntradasRespository;
import com.componente.factinven.repositorios.SalidasRespository;
import com.componente.factinven.repositorios.VentaRepositorio;
import com.componente.factinven.servicios.impl.FinanzasImpl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api")
public class FinanzasController {
	
	EntradasRespository entradasR;
	SalidasRespository salidasR;
	FinanzasImpl finanzasImpl;
	
	@Autowired
	EntradaMapper entradaMapper;
	
	@Autowired
	VentaRepositorio ventaRepositorio;
	
	public FinanzasController(EntradasRespository entradasR,SalidasRespository salidasR, FinanzasImpl finanzasImpl ) {
		this.entradasR=entradasR;
		this.salidasR=salidasR;
		this.finanzasImpl= finanzasImpl;
	}
	

	@PostMapping("entrada")
	public EntradaDto guardarEntrada(@RequestBody EntradaDto entradaBody) {
		Entrada entrada=entradaMapper.toEntity(entradaBody);
		entrada.setCreatedDate(new Date());
		 entrada= entradasR.save(entrada);
		return entradaMapper.toDto(entrada);
	}
	
	@PutMapping("entrada")
	public 	EntradaDto actualizarEntrada(@RequestBody EntradaDto entradaBody) {
		Entrada entrada=entradaMapper.toEntity(entradaBody);
		entrada.setUpdatedDate(new Date());
		entrada= entradasR.save(entrada);
		return entradaMapper.toDto(entrada);
	}
	
	@GetMapping("entrada/{entradaId}")
	public EntradaDto obtenerEntrada(@PathVariable int entradaId) {
		var entra=entradasR.findById(entradaId).get();
		var entrada= entradaMapper.toDto( entra);
		Venta venta= ventaRepositorio.findById(entra.getDetalleVenta().getVenta().getId()).get();
		entrada.setNombreCliente(venta.getCliente().getnombreCompletos());
		return entrada;
	}
	
	@DeleteMapping("entrada/{entradaId}")
	public void borrarEntrada(@PathVariable int entradaId) {
		entradasR.deleteById(entradaId);
	}
	
	
	@GetMapping("entradas")
	public List<EntradaDto> obtenerEntradas() {
		List<Entrada> listaEntrada= entradasR.findAll();
		List<EntradaDto> listaEntradaDto=  new ArrayList<>();
		for (Iterator iterator = listaEntrada.iterator(); iterator.hasNext();) {
			Entrada entrada = (Entrada) iterator.next();
			EntradaDto dto= entradaMapper.toDto(entrada);
			Venta venta= ventaRepositorio.findById(entrada.getDetalleVenta().getVenta().getId()).get();
			dto.setNombreCliente(venta.getCliente().getnombreCompletos());
			listaEntradaDto.add(dto);
		}
		return listaEntradaDto;
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
	
	

	
	@PostMapping("guardarPagos")
	public List<EntradaDto> guardarPagos(@RequestBody ArrayPagosDto arrayPagosDto) {
		List<Entrada> listaPago= new ArrayList<>();
		for (Iterator iterator = arrayPagosDto.listaPagos.iterator(); iterator.hasNext();) {
			EntradaDto entradaBody = (EntradaDto) iterator.next();
			Entrada entrada=entradaMapper.toEntity(entradaBody);
			entrada.setCreatedDate(new Date());
			entrada= entradasR.save(entrada);
			listaPago.add(entrada);
		}
		return entradaMapper.toDto(listaPago);
	}
	
	
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	static class  ArrayPagosDto {
		
		List<EntradaDto> listaPagos;
	}
	
	
	@GetMapping("pagosVenta/{ventaId}")
	public List<EntradaDto> pagosVenta(@PathVariable int ventaId) {
		List<Entrada> listaEntrada= entradasR.pagosDeVentas(ventaId);
		List<EntradaDto> listaEntradaDto=  new ArrayList<>();
		for (Iterator iterator = listaEntrada.iterator(); iterator.hasNext();) {
			Entrada entrada = (Entrada) iterator.next();
			EntradaDto dto= entradaMapper.toDto(entrada);
			dto.setNombreProducto(entrada.getDetalleVenta().getProducto().getNombre());
			listaEntradaDto.add(dto);
		}
		return listaEntradaDto;
	}
}
