package com.componente.factinven.servicios.interfaz;

import java.util.List;

import org.springframework.data.domain.Page;

import com.componente.factinven.dto.ComprobanteRequest;
import com.componente.factinven.dto.ComprobanteResponse;
import com.componente.factinven.dto.VentaResponse;
import com.componente.factinven.entidades.DetalleComprobante;
import com.componente.factinven.entidades.Entrada;
import com.componente.factinven.entidades.Salida;

public interface IComprobanteServicio {

	public abstract  ComprobanteResponse guardarComprobante(ComprobanteRequest comprobante);
	
	public abstract  ComprobanteResponse editarComprobante(ComprobanteRequest comprobante);
	
	public abstract  ComprobanteResponse buscarComprobanteCodigo(String codigo);
	
	public abstract  void eliminarComprobante(ComprobanteRequest comprobante);
	
	public abstract  void borrarComprobantes();

	public abstract  Page<ComprobanteResponse> listarComprobantes(int page, int size);
	
	public List<VentaResponse> findAll();
	
	public ComprobanteResponse findById(Integer id);
	
	public Entrada guardarEntrada(DetalleComprobante detalle);
	public Salida guardarSalida(DetalleComprobante detalle);
}
