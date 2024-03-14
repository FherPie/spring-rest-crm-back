package com.componente.factinven.servicios.interfaz;

import java.util.List;

import org.springframework.data.domain.Page;

import com.componente.factinven.dto.ComprobanteRequest;
import com.componente.factinven.dto.VentaResponse;
import com.componente.factinven.entidades.Entrada;
import com.componente.factinven.entidades.Salida;

public interface IComprobanteServicio {

	public abstract  VentaResponse guardarComprobante(ComprobanteRequest comprobante);
	
	public abstract  VentaResponse editarComprobante(ComprobanteRequest comprobante);
	
	public abstract  VentaResponse buscarComprobanteCodigo(String codigo);
	
	public abstract  void eliminarComprobante(ComprobanteRequest comprobante);
	
	public abstract  void borrarComprobantes();

	public abstract  Page<VentaResponse> listarComprobantes(int page, int size);
	
	public List<VentaResponse> findAll();
	
	public VentaResponse findById(Integer id);

}
