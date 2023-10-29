package com.componente.factinven.servicios.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.componente.factinven.dto.ComprobanteRequest;
import com.componente.factinven.dto.ComprobanteResponse;
import com.componente.factinven.dto.VentaRequest;
import com.componente.factinven.dto.VentaRequest.DetalleVentaRequest;
import com.componente.factinven.dto.VentaResponse;
import com.componente.factinven.entidades.DetalleComprobante;
import com.componente.factinven.entidades.DetalleVenta;
import com.componente.factinven.entidades.Entrada;
import com.componente.factinven.entidades.Salida;
import com.componente.factinven.entidades.Venta;
import com.componente.factinven.repositorios.AlmacenRepositorio;
import com.componente.factinven.repositorios.ClienteRepositorio;
import com.componente.factinven.repositorios.ComprobanteRepositorio;
import com.componente.factinven.repositorios.DetalleComprobanteRepositorio;
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
	ComprobanteRepositorio comprobanteRespositorio;

	@Autowired
	VentaRepositorio ventaRespositorio;

	@Autowired
	AlmacenRepositorio almacenRespositorio;

	@Autowired
	ClienteRepositorio clienteRespositorio;

	@Autowired
	EmpleadoRepositorio empleadoRespositorio;

	@Autowired
	DetalleComprobanteRepositorio detalleComprobanteRespositorio;

	@Autowired
	DetalleVentaRepositorio detalleVentaRepositorio;

	@Autowired
	ProductoRepositorio productoRespositorio;

	@Autowired
	EntradasRespository entradaRespositorio;

	@Autowired
	SalidasRespository salidaRespositorio;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


	DecimalFormat df = new DecimalFormat("0.00");

	@Transactional
	@Override
	public ComprobanteResponse guardarComprobante(ComprobanteRequest comprobanteRequest) {
		VentaRequest ventaRequest = (VentaRequest) comprobanteRequest;
		comprobanteRequest.setFechaEmision(LocalDateTime.now());
		// System.out.println(ventaRequest);
		Venta venta = new Venta();
		venta.setCodigo(ventaRequest.getCodigo());
		venta.setEstado(ventaRequest.getEstado());
		// venta.setAlmacen(almacenRespositorio.findById(comprobanteRequest.getIdAlmacen()).orElse(null));
		// System.out.println(clienteRespositorio.findById(ventaRequest.getIdCliente()).get());
		venta.setCliente(clienteRespositorio.findById(ventaRequest.getIdCliente()).orElse(null));
		// venta.setEmpleado(empleadoRespositorio.findById(comprobanteRequest.getIdEmpleado()).orElse(null));
		venta.setTotal(ventaRequest.getTotal());
		venta.setFormaPago(ventaRequest.getFormaPago());
		venta.setFechayHora(ventaRequest.getFechaEmision());
		venta = comprobanteRespositorio.save(venta);
		System.out.println("DETALLE");
		for (DetalleVentaRequest det : ventaRequest.getItemsFactura()) {
			DetalleVenta deta = new DetalleVenta();
			deta.setProducto(productoRespositorio.findById(det.getProductoId()).orElse(null));
			deta.setPrecioUnitario(new BigDecimal(det.getPrecioUnitario()));
			deta.setCantidad(det.getNumeroItems());
			deta.setUnidad(0);
			deta.setVenta(venta);
			deta = detalleComprobanteRespositorio.save(deta);
			guardarSalida(deta);
		}

		return new VentaResponse(comprobanteRespositorio.save(venta));
	}

	@Override
	public ComprobanteResponse editarComprobante(ComprobanteRequest comprobante) {
		Venta venta = new Venta();
		venta.setCodigo(comprobante.getCodigo());
		return new VentaResponse(comprobanteRespositorio.save(venta));

	}

	@Override
	public ComprobanteResponse buscarComprobanteCodigo(String codigo) {
		return new ComprobanteResponse(comprobanteRespositorio.findByCodigo(codigo));
	}

	@Override
	public void eliminarComprobante(ComprobanteRequest comprobante) {
		// TODO Auto-generated method stub

	}

	@Override
	public void borrarComprobantes() {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<ComprobanteResponse> listarComprobantes(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VentaResponse> findAll() {
		List<VentaResponse> listaRetorna = new ArrayList<>();
		ventaRespositorio.findAll().forEach((n) -> {
			VentaResponse venta = new VentaResponse();
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
	public ComprobanteResponse findById(Integer id) {
		return new ComprobanteResponse(comprobanteRespositorio.findById(id).get());
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
			//venta.setTotal(new BigDecimal(1000));
			// List<Detalle> lista= detalleComprobanteRespositorio.fin;
			listaRetorna.add(venta);
		});
		return listaRetorna;
	}

	@Override
	public Entrada guardarEntrada(DetalleComprobante detalle) {
		Entrada entrada = new Entrada();
		entrada.setDetalleComprobante(detalle);
		return entrada = entradaRespositorio.save(entrada);
	}

	@Override
	public Salida guardarSalida(DetalleComprobante detalle) {
		// TODO Auto-generated method stub
		Salida salida = new Salida();
		salida.setDetalleComprobante(detalle);
		return salida = salidaRespositorio.save(salida);
	}

}
