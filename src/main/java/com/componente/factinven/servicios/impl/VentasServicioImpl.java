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
import com.componente.factinven.servicios.interfaz.IDetalleVentasServicio;

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
	IDetalleVentasServicio detalleVentasServicio;
	
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
		//VentaResponse ventaRequest =  comprobanteRequest;
		calculoTotal(comprobanteRequest);
		comprobanteRequest.setFechayHora(LocalDateTime.now());
		Venta venta = new Venta();
		
		//venta.setCliente(clienteRespositorio.findById(ventaRequest.getIdCliente().getId()).orElse(null));
		//venta.setFechayHora(ventaRequest.getFechayHora());

		venta = ventaMapper.toEntity(comprobanteRequest);
		venta.setDetallesVenta(new ArrayList<>());
		armarDetalles(comprobanteRequest.getDetallesVentaDto(), venta);
		venta.setEstado("1");
		
		venta = ventaRespositorio.save(venta);
		return ventaMapper.toDto(venta);
	}
	
	@Transactional
	@Override
	public VentaResponse guardaryCerrarComprobante(VentaResponse comprobanteRequest) {
		//VentaResponse ventaRequest =  comprobanteRequest;
		calculoTotal(comprobanteRequest);
		comprobanteRequest.setFechayHora(LocalDateTime.now());
		Venta venta = new Venta();
		
		//venta.setCliente(clienteRespositorio.findById(ventaRequest.getIdCliente().getId()).orElse(null));
		//venta.setFechayHora(ventaRequest.getFechayHora());

		venta = ventaMapper.toEntity(comprobanteRequest);
		venta.setDetallesVenta(new ArrayList<>());
		armarDetalles(comprobanteRequest.getDetallesVentaDto(), venta);
		venta.setEstado("2");
		
		venta = ventaRespositorio.save(venta);
		return ventaMapper.toDto(venta);
	}


	private Double calcularTotalComprobante( List<DetalleVentaDto> lista){
		Double total= 0.0;
		for (DetalleVentaDto det : lista) {
			DetalleVenta deta =detalleVentaMapper.toEntity(det);
			total+= deta.getPrecioUnitario()*deta.getCantidad();
		}
		
        return total;
	}
	
	private Double calcularDescuentoTotalComprobante( List<DetalleVentaDto> lista){
		Double total= 0.0;
		for (DetalleVentaDto det : lista) {
			DetalleVenta deta =detalleVentaMapper.toEntity(det);
			total+= deta.getDescuentoUnitario();
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
		calculoTotal(comprobanteRequest);
		Venta venta= ventaMapper.toEntity(ventaRequest);
		venta.removeDetails();
		armarDetalles(ventaRequest.getDetallesVentaDto(), venta);
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
		ventaRespositorio.findByEstadoOrderByIdDesc("1").forEach((n) -> {
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
			Double total = 0.0;
			for (DetalleVenta detalle : listaDetalles) {
				total += detalle.getCantidad()*detalle.getPrecioUnitario();
			}
			//venta.setTotal(df.format(total));
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
			Double total = 0.0;
			List<DetalleVenta> listaDetalles = detalleVentaRepositorio.listaDetallesVenta(n);
			for (DetalleVenta detalle : listaDetalles) {
				total += detalle.getCantidad()*detalle.getPrecioUnitario();
			}
			//venta.setTotal(df.format(total));
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
		calculoTotal(comprobanteRequest);
		comprobanteRequest.addDetailOnTop(detalleAgregado);
		return comprobanteRequest;
	}
	
	public VentaResponse removeDetalle(VentaResponse comprobanteRequest) {
		calculoTotal(comprobanteRequest);
		return comprobanteRequest;
	}
	
	public VentaResponse doneDetalle(VentaResponse comprobanteRequest) {
		calculoTotal(comprobanteRequest);
		return comprobanteRequest;
	}
	
	
	
	private void calculoTotal(VentaResponse comprobanteRequest) {
		Double total= calcularTotalComprobante(comprobanteRequest.getDetallesVentaDto());
		Double totalD= calcularDescuentoTotalComprobante(comprobanteRequest.getDetallesVentaDto());
		comprobanteRequest.setTotalSinDescuento(total);
		comprobanteRequest.setTotalDescuento(totalD);
	    comprobanteRequest.setTotal(total-totalD);
	}
	
	
	public List<VentaResponse> findAllOrdenes() {
		List<VentaResponse> listaRetorna = new ArrayList<>();
		ventaRespositorio.findByEstadoOrderByIdDesc("2").forEach((n) -> {
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
			Double total = 0.0;
			for (DetalleVenta detalle : listaDetalles) {
				total += detalle.getCantidad()*detalle.getPrecioUnitario();
			}
			//venta.setTotal(df.format(total));
			listaRetorna.add(venta);
		});
		return listaRetorna;
	}

	@Override
	public List<VentaResponse> listarOrdernesConSaldo() {
		List<VentaResponse> listaRetorna = new ArrayList<>();
		List<VentaResponse> listaRespuesta = new ArrayList<>();
		listaRetorna= ventaMapper.toDto(ventaRespositorio.findByEstadoOrderByIdDesc("2"));
		listaRetorna.forEach((n) -> {
			VentaResponse venta = new VentaResponse();
			n.setFechaFormat(n.getFechayHora().format(formatter));
			//venta.setNombreCliente(n.getCliente().getPersona().getNombres() + " " + n.getCliente().getPersona().getApellidos());
			String formattedNumeroFactura = String.format("%06d", n.getId());
			n.setNumeroFactura(formattedNumeroFactura);
	         Boolean saldoAlaFecha= detalleVentasServicio.algunDetalleConSaldo(n.getId());
			if(saldoAlaFecha); {
				List<DetalleVentaDto> listaDetalles = detalleVentasServicio.listarDetallesVenta(n.getId());
				Double total = 0.0;
				for (DetalleVentaDto detalle : listaDetalles) {
					total += detalle.getCantidad()*detalle.getPrecioUnitario();
				}
				listaRespuesta.add(n);
			}
		});
		return listaRespuesta;
	}


	


}
