package com.componente.factinven.dto;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class VentaResponse {

	private static final long serialVersionUID = 8552336462521975417L;
	private Integer id;
	private LocalDateTime fechayHora;
	private Integer idEmpleado;	
	private ClienteDto idCliente;
	private String estado; 
	private String codigo;
	private Integer idAlmacen;
	private String formaPago;
	private Double total;
	private Double totalSinDescuento;
	private Double totalDescuento;
	private String nombreCliente;
	private String fechaFormat;
	private String numeroFactura;
	private String motivoConsulta;
	//private List<DetalleVentaDto> detallesVentaDto;
	private List<DetalleVentaDto> detallesVentaDto = new LinkedList<>();
	
	public VentaResponse() {}
	
	
	
	
	public VentaResponse(Integer id, LocalDateTime fechayHora, String estado, String codigo,
			String formaPago, Double total) {
		super();
		this.id = id;
		this.fechayHora = fechayHora;
		this.estado = estado;
		this.codigo = codigo;
		this.formaPago = formaPago;
		this.total = total;
	}




	public void addDetail(DetalleVentaDto detalleVenta) {
		this.detallesVentaDto.add(detalleVenta);
		detalleVenta.setVenta(this);
	}
	
	public void addDetailOnTop(DetalleVentaDto detalleVenta) {
		this.detallesVentaDto.add(0,detalleVenta);
		//detalleVenta.setVenta(this);
	}
	
	
	public void removeDetail(DetalleVentaDto detalleVenta) {
		this.detallesVentaDto.remove(detalleVenta);
		detalleVenta.setVenta(null);
	}
	
	public void removeDetails() {
		
		Iterator<DetalleVentaDto> iterator= this.detallesVentaDto.iterator();
		
		while(iterator.hasNext()) {
			DetalleVentaDto detalleVenta= iterator.next();
			detalleVenta.setVenta(null);
			iterator.remove();
		}
		

	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDateTime getFechayHora() {
		return fechayHora;
	}
	public void setFechayHora(LocalDateTime fechayHora) {
		this.fechayHora = fechayHora;
	}
	public Integer getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public ClienteDto getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(ClienteDto idCliente) {
		this.idCliente = idCliente;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Integer getIdAlmacen() {
		return idAlmacen;
	}
	public void setIdAlmacen(Integer idAlmacen) {
		this.idAlmacen = idAlmacen;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getFechaFormat() {
		return fechaFormat;
	}
	public void setFechaFormat(String fechaFormat) {
		this.fechaFormat = fechaFormat;
	}
	public String getNumeroFactura() {
		return numeroFactura;
	}
	public VentaResponse setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
		return this;
	}
//	public List<DetalleVentaDto> getDetallesVentaDto() {
//		return detallesVentaDto;
//	}
//	public void setDetallesVentaDto(List<DetalleVentaDto> detallesVentaDto) {
//		this.detallesVentaDto = detallesVentaDto;
//	}
	public List<DetalleVentaDto> getDetallesVentaDto() {
		return detallesVentaDto;
	}
	public void setDetallesVentaDto(List<DetalleVentaDto> detallesVentaDto) {
		this.detallesVentaDto = detallesVentaDto;
	}




	public Double getTotalSinDescuento() {
		return totalSinDescuento;
	}




	public void setTotalSinDescuento(Double totalSinDescuento) {
		this.totalSinDescuento = totalSinDescuento;
	}




	public Double getTotalDescuento() {
		return totalDescuento;
	}




	public void setTotalDescuento(Double totalDescuento) {
		this.totalDescuento = totalDescuento;
	}
	
	

	

}
