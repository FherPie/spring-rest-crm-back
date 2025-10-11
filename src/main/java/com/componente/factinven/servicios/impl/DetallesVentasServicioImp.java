package com.componente.factinven.servicios.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.componente.factinven.dto.DetalleVentaDto;
import com.componente.factinven.entidades.DetalleVenta;
import com.componente.factinven.entidades.Entrada;
import com.componente.factinven.entidades.Venta;
import com.componente.factinven.mappers.ClienteMapper;
import com.componente.factinven.mappers.DetalleVentaMapper;
import com.componente.factinven.mappers.VentaMapper;
import com.componente.factinven.repositorios.DetalleVentaRepositorio;
import com.componente.factinven.repositorios.EntradasRespository;
import com.componente.factinven.repositorios.SalidasRespository;
import com.componente.factinven.repositorios.VentaRepositorio;
import com.componente.factinven.servicios.interfaz.IDetalleVentasServicio;

@Service
public class DetallesVentasServicioImp implements IDetalleVentasServicio {


	@Autowired
	VentaRepositorio ventaRespositorio;

	@Autowired
	DetalleVentaRepositorio detalleVentaRepositorio;



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


	@Override
	public List<DetalleVentaDto> listarDetalles() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override  ///ESTE METODO LISTA LOS DETALLES CON SALDO
	public List<DetalleVentaDto> listarDetallesVenta(Integer idVenta) {
        Venta venta= ventaRespositorio.findById(idVenta).get();
        List<DetalleVenta> lista= detalleVentaRepositorio.listaDetallesVenta(venta);
        
        for (DetalleVenta detalleVenta : lista) {
        	double total= (detalleVenta.getCantidad()*detalleVenta.getPrecioUnitario())-detalleVenta.getDescuentoUnitario();
        	 List<Entrada> entrada= entradaRespositorio.pagosDetalle(detalleVenta.getId());
        	 double totalpago=0.0;
        	 for (Entrada detalleVenta2 : entrada) {
        		 totalpago+=detalleVenta2.getPrecio();
			}
        	 detalleVenta.setSaldo(total-totalpago);
		}
        
        //remover para que solo me muestre los detalles con saldo
        for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
			DetalleVenta detalleVenta = (DetalleVenta) iterator.next();
			if(detalleVenta.getSaldo()<=0) {
				iterator.remove();
			}
		}
		return detalleVentaMapper.toDto(lista);
	}
	

	@Override
	public Boolean algunDetalleConSaldo(Integer idVenta) {
		     List<DetalleVentaDto>  lista= listarDetallesVenta(idVenta) ;
	        //remover para que solo me muestre los detalles con saldo
	        for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
	        	DetalleVentaDto detalleVenta = (DetalleVentaDto) iterator.next();
				if(detalleVenta.getSaldo()>0) {
                 return true;
		      }
			}
	        return false;
	}


	@Override
	public List<DetalleVentaDto> formasPago() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DetalleVentaDto findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DetalleVentaDto create(DetalleVentaDto detalle) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DetalleVentaDto update(DetalleVentaDto detalle) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}



	


}
