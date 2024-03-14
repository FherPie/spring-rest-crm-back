package com.componente.factinven.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.componente.factinven.entidades.DetalleVenta;
import com.componente.factinven.entidades.Venta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VentaResponse extends ComprobanteResponse {

	private static final long serialVersionUID = -2726245420729865368L;
	private List<DetalleVentaDto> detallesVentaDto;
	public VentaResponse() {}
	  

}
