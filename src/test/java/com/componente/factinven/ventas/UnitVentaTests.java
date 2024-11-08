package com.componente.factinven.ventas;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.componente.factinven.dto.VentaResponse;
import com.componente.factinven.servicios.impl.VentasServicioImpl;

//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
//@SpringBootTest
//@ActiveProfiles("test")
//@TestMethodOrder(OrderAnnotation.class)
public class UnitVentaTests {

	@Autowired
	private VentasServicioImpl ventaServicio;
	

	@Test
	@Order(1)    
	public void crearVenta() {
		VentaResponse  ventita= new VentaResponse();
		ventita.setCodigo("001");
		ventita.setEstado("Nueva");
		ventita.setIdAlmacen(1);
		//ventita.setIdCliente(1);
		ventita.setIdEmpleado(1);
		//ventita.setTotal(new BigDecimal(50));
		ventita.setFormaPago("Tarjeta");		
		//ventita.setFechaEmision(LocalDateTime.now());
		
//		
//		DetalleVentaRequest detalle= new DetalleVentaRequest();
//		detalle.setDescuentoUnitario(0);
//		detalle.setNumeroDetalle(1);
//		detalle.setNumeroItems(1);
//		detalle.setPrecioUnitario(10);
//		detalle.setProductoId(34);
//		
//		List<DetalleVentaRequest> listaD= new ArrayList<>();
//		listaD.add(detalle);
//		ventita.setItemsFactura(listaD);
//		
//		
//		
//		VentaResponse venta=ventaServicio.guardarComprobante(ventita);
//		System.out.println(venta.toString());
//		System.out.println(ventita.toString());
	    // assertNotEquals(venta.getId(), 0);
	}
	
	
	@Test
	@Order(2)
	public void listarVentas() {

	}
		

	@Test
	@Order(3)
	public void buscarEmpleado() {
	
			
	}
	

}
