package com.componente.factinven.servicios.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.componente.factinven.dto.EntradaDto;
import com.componente.factinven.entidades.Entrada;
import com.componente.factinven.entidades.Salida;
import com.componente.factinven.entidades.Venta;
import com.componente.factinven.repositorios.EntradasRespository;
import com.componente.factinven.repositorios.SalidasRespository;
import com.componente.factinven.repositorios.VentaRepositorio;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
public class FinanzasImpl {
	
	@Autowired
	private EntradasRespository ingresosR;
	
	@Autowired
	private SalidasRespository salidasR;
	
	
	@Autowired
	private  VentaRepositorio ventaRepo;
	
	
	
	
	public List<EntradaDto> pagosVenta(Integer ventaId){
		
		Venta venta= ventaRepo.getById(ventaId);
		
		
		return null;
	}
	
	
	
	public ResultadosDto resultados() {
        var cal= Calendar.getInstance();
        var month= cal.get(Calendar.MONTH)+1;
        List<Entrada> listaIngresos=ingresosR.entradasMes(month);
        List<Salida> listaEgresos= salidasR.salidasMes(month);
        var totalIngresos=0.0;
        var totalEgresos=0.0;
        var resultadoTotal=0.0;

         for(Salida salida: listaEgresos) {
        	 totalEgresos+=salida.getPrecio();
         }
        
		for(Entrada entrada: listaIngresos) {
			totalIngresos+=entrada.getPrecio();
		}
		resultadoTotal=totalIngresos-totalEgresos;
		var resultado= new ResultadosDto(totalIngresos, totalEgresos, resultadoTotal);
		return resultado;
	}
	
	 
	
	@Data
	@AllArgsConstructor
	public class ResultadosDto {
		private Double totalIngresos;
		private Double totalEgresos;
		private Double total;
	}

	

}


 
