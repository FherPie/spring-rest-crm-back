package com.componente.factinven.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.componente.factinven.dto.DetalleVentaDto;
import com.componente.factinven.responses.ResponseGenerico;
import com.componente.factinven.servicios.interfaz.IDetalleVentasServicio;
import com.componente.factinven.utils.ControllersUtils;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class DetalleVentaController {

	@Autowired
	IDetalleVentasServicio detalleVentasService;

    
	@GetMapping("/listarDetallesVenta/{idVenta}")
	public ResponseEntity<?> listarDetallesVenta(@PathVariable Integer idVenta) {
	     ResponseGenerico<List<DetalleVentaDto>> response = new ResponseGenerico<>();
	     List<DetalleVentaDto> ventasListado= detalleVentasService.listarDetallesVenta(idVenta);
		 return ControllersUtils.repuestaGenericoExitoList(response, ventasListado);
	}
	
	
//	@GetMapping("/saldoDetalle/{idDetalleVenta}")
//	public ResponseEntity<?> saldoDetalle(@PathVariable Integer idDetalleVenta) {
//	     ResponseGenerico<List<DetalleVentaDto>> response = new ResponseGenerico<>();
//	     List<DetalleVentaDto> ventasListado= detalleVentasService.listarDetallesVenta(idVenta);
//		 return ControllersUtils.repuestaGenericoExitoList(response, ventasListado);
//	}
	
	

	

}
