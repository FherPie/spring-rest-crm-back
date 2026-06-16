package com.componente.factinven.servicios.impl;

import com.componente.factinven.dto.VentaResponse;
import com.componente.factinven.entidades.Venta;
import com.componente.factinven.mappers.ClienteMapper;
import com.componente.factinven.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class MensajesWhatsappServicioImpl {


	@Autowired
	MensajesWhastsappRepositorio mensajesWhastsappRepositorio;

	@Autowired
	AlmacenRepositorio almacenRespositorio;

	@Autowired
	ClienteRepositorio clienteRespositorio;

	@Autowired
	EmpleadoRepositorio empleadoRespositorio;

	@Autowired
	ProductoRepositorio productoRepositorio;

	@Autowired
	private ClienteMapper clienteMapper;


	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	DecimalFormat df = new DecimalFormat("0.00");

	@Transactional
	public VentaResponse guardarMensajeWhatsapp(VentaResponse comprobanteRequest) {
		//VentaResponse ventaRequest =  comprobanteRequest;
		//calculoTotal(comprobanteRequest);
		comprobanteRequest.setFechayHora(LocalDateTime.now());
		Venta venta = new Venta();

		//venta.setCliente(clienteRespositorio.findById(ventaRequest.getIdCliente().getId()).orElse(null));
		//venta.setFechayHora(ventaRequest.getFechayHora());

		//venta = ventaMapper.toEntity(comprobanteRequest);
		//venta.setDetallesVenta(new ArrayList<>());
		//armarDetalles(comprobanteRequest.getDetallesVentaDto(), venta);
		venta.setEstado("1");

		//venta = ventaRespositorio.save(venta);
		return null;

	}
}
