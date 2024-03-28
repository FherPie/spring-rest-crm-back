package com.componente.factinven.servicios.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.componente.factinven.dto.DetalleVentaDto;
import com.componente.factinven.dto.ProductoDto;
import com.componente.factinven.dto.VentaResponse;
import com.componente.factinven.entidades.DetalleVenta;
import com.componente.factinven.entidades.Venta;
import com.componente.factinven.mappers.ClienteMapper;
import com.componente.factinven.mappers.DetalleVentaMapper;
import com.componente.factinven.mappers.VentaMapper;
import com.componente.factinven.repositorios.AlmacenRepositorio;
import com.componente.factinven.repositorios.ClienteRepositorio;
import com.componente.factinven.repositorios.DetalleVentaRepositorio;
import com.componente.factinven.repositorios.EmpleadoRepositorio;
import com.componente.factinven.repositorios.EntradasRespository;
import com.componente.factinven.repositorios.ProductoRepositorio;
import com.componente.factinven.repositorios.SalidasRespository;
import com.componente.factinven.repositorios.VentaRepositorio;
import com.componente.factinven.servicios.interfaz.IComprobanteServicio;

@Service
public class VentasServicioImpl implements IComprobanteServicio {


	@Autowired
	VentaRepositorio ventaRespositorio;

	@Autowired
	AlmacenRepositorio almacenRespositorio;

	@Autowired
	ClienteRepositorio clienteRespositorio;

	@Autowired
	EmpleadoRepositorio empleadoRespositorio;

	@Autowired
	DetalleVentaRepositorio detalleVentaRepositorio;

	@Autowired
	ProductoRepositorio productoRespositorio;

	@Autowired
	EntradasRespository entradaRespositorio;

	@Autowired
	SalidasRespository salidaRespositorio;
	
	@Autowired
	private ClienteMapper clienteMapper;
	
	@Autowired
	private VentaMapper ventaMapper;
	
	@Autowired
	private DetalleVentaMapper detalleVentaMapper;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


	DecimalFormat df = new DecimalFormat("0.00");

	@Transactional
	@Override
	public VentaResponse guardarComprobante(VentaResponse comprobanteRequest) {
		VentaResponse ventaRequest =  comprobanteRequest;
		comprobanteRequest.setFechayHora(LocalDateTime.now());
		Venta venta = new Venta();
		venta.setFormaPago("EFECTIVO");
		venta.setCodigo("001");
		venta.setEstado("VALIDO");
		venta.setCliente(clienteRespositorio.findById(ventaRequest.getIdCliente().getId()).orElse(null));
		venta.setFechayHora(ventaRequest.getFechayHora());
		armarDetalles(ventaRequest.getDetallesVentaDto(), venta);
		BigDecimal total= calcularTotalComprobante(ventaRequest.getDetallesVentaDto());
		venta.setTotal(total);
		venta = ventaRespositorio.save(venta);
		return ventaMapper.toDto(ventaRespositorio.save(venta));
	}

	private BigDecimal calcularTotalComprobante( List<DetalleVentaDto> lista){
		BigDecimal total= new BigDecimal(0);
		for (DetalleVentaDto det : lista) {
			DetalleVenta deta =detalleVentaMapper.toEntity(det);
			total= deta.getPrecioUnitario().multiply(new BigDecimal(deta.getCantidad()));
		}
		
        return total;
	}
	
	
	private void armarDetalles( List<DetalleVentaDto> lista, Venta venta){
		BigDecimal total= new BigDecimal(0);
		for (DetalleVentaDto det : lista) {
			DetalleVenta deta =detalleVentaMapper.toEntity(det);
			venta.addDetail(deta);
		}
	}
	
	
	
	
	@Transactional
	public VentaResponse actualizarComprobante(VentaResponse comprobanteRequest) {
		VentaResponse ventaRequest =  comprobanteRequest;
		Venta venta= ventaMapper.toEntity(ventaRequest);
		venta.removeDetails();
		armarDetalles(ventaRequest.getDetallesVentaDto(), venta);
		BigDecimal total= calcularTotalComprobante(ventaRequest.getDetallesVentaDto());
		venta.setTotal(total);
		venta = ventaRespositorio.save(venta);
		return ventaMapper.toDto(ventaRespositorio.save(venta));
	}



	@Override
	public VentaResponse buscarComprobanteCodigo(String codigo) {
		return new VentaResponse();
	}


	@Override
	public void borrarComprobantes() {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<VentaResponse> listarComprobantes(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VentaResponse> findAll() {
		List<VentaResponse> listaRetorna = new ArrayList<>();
		ventaRespositorio.findAllByOrderByIdDesc().forEach((n) -> {
			VentaResponse venta = new VentaResponse();
			venta.setId(n.getId());
			venta.setEstado(n.getEstado());
			venta.setFechaFormat(n.getFechayHora().format(formatter));
			venta.setFormaPago(n.getFormaPago());
			venta.setNombreCliente(
					n.getCliente().getPersona().getNombres() + " " + n.getCliente().getPersona().getApellidos());
			String formattedNumeroFactura = String.format("%06d", n.getId());
			venta.setNumeroFactura(formattedNumeroFactura);
			List<DetalleVenta> listaDetalles = detalleVentaRepositorio.listaDetallesVenta(n);
			BigDecimal total = new BigDecimal(0);
			for (DetalleVenta detalle : listaDetalles) {
				total = total.add(new BigDecimal(detalle.getCantidad()).multiply(detalle.getPrecioUnitario()));
			}
			venta.setTotal(df.format(total));
			listaRetorna.add(venta);
		});
		return listaRetorna;
	}

	@Override
	public VentaResponse findById(Integer id) {
		Venta venta=ventaRespositorio.findById(id).get();
		return ventaMapper.toDto(venta).setNumeroFactura(numeroVentaFormateado(venta.getId()));
		//return new VentaResponse(comprobanteRespositorio.findById(id).get());
	}

	public List<VentaResponse> getAllMovimientoByClienteId(Integer clienteId, LocalDateTime startDate,
			LocalDateTime endDate) {
		List<VentaResponse> listaRetorna = new ArrayList<>();
		ventaRespositorio.fecthVentaBetweenDatesAndClientID(startDate, endDate, clienteId).forEach((n) -> {
			VentaResponse venta = new VentaResponse();
			venta.setEstado(n.getEstado());
			venta.setFechayHora(n.getFechayHora());
			venta.setFormaPago(n.getFormaPago());
			venta.setNombreCliente(
					n.getCliente().getPersona().getNombres() + " " + n.getCliente().getPersona().getApellidos());
			venta.setId(n.getId());
			venta.setFechaFormat(n.getFechayHora().format(formatter));
			venta.setNumeroFactura(numeroVentaFormateado(n.getId()));
			BigDecimal total = new BigDecimal(0);
			List<DetalleVenta> listaDetalles = detalleVentaRepositorio.listaDetallesVenta(n);
			for (DetalleVenta detalle : listaDetalles) {
				total = total.add(new BigDecimal(detalle.getCantidad()).multiply(detalle.getPrecioUnitario()));
			}
			venta.setTotal(df.format(total));
			//venta.setTotal(new BigDecimal(1000));
			// List<Detalle> lista= detalleComprobanteRespositorio.fin;
			listaRetorna.add(venta);
		});
		return listaRetorna;
	}

	@Override
	public VentaResponse editarComprobante(VentaResponse comprobante) {
		Venta venta = new Venta();
		venta.setCodigo(comprobante.getCodigo());
		return ventaMapper.toDto(ventaRespositorio.save(venta));
	}

	@Override
	public boolean eliminarComprobante(Integer id) {
         Venta venta= ventaRespositorio.findById(id).get();
         venta.removeDetails();
         ventaRespositorio.delete(venta);
         return true;
	}
	
	
	public VentaResponse getVenta() {
		Venta venta= new Venta();
		venta.setFechayHora(LocalDateTime.now());
		venta.setCodigo(null);
		return ventaMapper.toDto(venta);
		//return new VentaResponse(comprobanteRespositorio.findById(id).get());
	}
	
	private String numeroVentaFormateado(Integer id) {
		return String.format("%06d", id); 
	}

	
	public VentaResponse addDetalle(VentaResponse comprobanteRequest) {
		DetalleVentaDto detalleAgregado= new DetalleVentaDto();
		detalleAgregado.setEdit(true);
		detalleAgregado.setProductoDto(new ProductoDto());
		comprobanteRequest.addDetailOnTop(detalleAgregado);
		return comprobanteRequest;
	}


}
