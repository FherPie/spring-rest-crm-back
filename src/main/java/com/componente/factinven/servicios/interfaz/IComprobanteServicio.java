package com.componente.factinven.servicios.interfaz;

import java.util.List;

import org.springframework.data.domain.Page;

import com.componente.factinven.dto.VentaResponse;
import com.componente.factinven.entidades.Entrada;
import com.componente.factinven.entidades.Salida;

public interface IComprobanteServicio {

	public abstract  VentaResponse guardarComprobante(VentaResponse comprobante);
	
	public abstract  VentaResponse editarComprobante(VentaResponse comprobante);
	
	public abstract  VentaResponse buscarComprobanteCodigo(String codigo);
	
	public abstract  void eliminarComprobante(VentaResponse comprobante);
	
	public abstract  void borrarComprobantes();

	public abstract  Page<VentaResponse> listarComprobantes(int page, int size);
	
	public List<VentaResponse> findAll();
	
	public VentaResponse findById(Integer id);

}
