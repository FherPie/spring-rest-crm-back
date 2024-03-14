package com.componente.factinven.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.componente.factinven.entidades.DetalleVenta;
import com.componente.factinven.entidades.Venta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VentaResponse {

	private static final long serialVersionUID = 8552336462521975417L;
	private Integer id;
	private LocalDateTime fechayHora;
	private Integer idEmpleado;	
	private ClienteResponse idCliente;
	private String estado; 
	private String codigo;
	private Integer idAlmacen;
	private String formaPago;
	private String total;
	private String nombreCliente;
	private String fechaFormat;
	private String numeroFactura;
	private List<DetalleVentaDto> detallesVentaDto;
	public VentaResponse() {}
	  

}
