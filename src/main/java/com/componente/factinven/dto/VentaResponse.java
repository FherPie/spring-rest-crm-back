package com.componente.factinven.dto;

import com.componente.factinven.entidades.Venta;

public class VentaResponse extends ComprobanteResponse {

	private static final long serialVersionUID = -2726245420729865368L;

	public VentaResponse( Venta venta) {
		super();
		this.setId(venta.getId());
		this.setCodigo(venta.getCodigo());
		//this.setTotal(venta.getTotal().toString());
		this.setFechayHora(venta.getFechayHora());
		this.setEstado(venta.getEstado());
		//this.setIdCliente(venta.getCliente().getId());
		//this.setIdAlmacen(venta.getAlmacen().getId());
		//this.setIdEmpleado(venta.getEmpleado().getId());
		this.setFormaPago(venta.getFormaPago());
	}

	public VentaResponse(Object save) {
		Venta venta= (Venta)save;
		this.setId(venta.getId());
		this.setCodigo(venta.getCodigo());
		this.setTotal(venta.getTotal().toString());
		this.setFechayHora(venta.getFechayHora());
		this.setEstado(venta.getEstado());
		//this.setIdCliente(venta.getCliente().getId());
		//this.setIdAlmacen(venta.getAlmacen().getId());
		//this.setIdEmpleado(venta.getEmpleado().getId());
		this.setFormaPago(venta.getFormaPago());
	}

	
	public VentaResponse() {}
	  

}
