package com.componente.factinven.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.componente.factinven.entidades.Comprobante;
import com.componente.factinven.mappers.ClienteMapper;

import lombok.Data;

@Data
public class ComprobanteResponse  implements Serializable {

  
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
	
	
	
	public ComprobanteResponse(Comprobante comprobante) {
		super();
		this.id = comprobante.getId();
		this.fechayHora = comprobante.getFechayHora();
		//this.idEmpleado = comprobante.getEmpleado().getId();
		//this.idCliente = comprobante.getCliente();
		this.estado = comprobante.getEstado();
		this.codigo = comprobante.getCodigo();
		//this.idAlmacen = comprobante.getAlmacen().getId();
		this.formaPago = comprobante.getFormaPago();
		this.total = comprobante.getTotal().toString();
	}



	public ComprobanteResponse() {
		// TODO Auto-generated constructor stub
	}



	public ComprobanteResponse(Object object) {
		super();
		Comprobante comprobante= (Comprobante) object;
		this.id = comprobante.getId();
		this.fechayHora = comprobante.getFechayHora();
		this.idEmpleado = comprobante.getEmpleado().getId();
		//this.idCliente =comprobante.getCliente();
		this.estado = comprobante.getEstado();
		this.codigo = comprobante.getCodigo();
		this.idAlmacen = comprobante.getAlmacen().getId();
		this.formaPago = comprobante.getFormaPago();
		this.total = comprobante.getTotal().toString();
	}



	
	
	
	
}
