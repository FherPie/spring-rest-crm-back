package com.componente.factinven.unittests.persona;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
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

import com.componente.factinven.entidades.Proveedor;
import com.componente.factinven.servicios.impl.ProveedorServicioImpl;

//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
//@SpringBootTest
//@ActiveProfiles("test")
//@TestMethodOrder(OrderAnnotation.class)
public class UnitProveedorTests {

	// 1 Microincremenrto Repositories

	// Crear Empleado
	@Autowired
	private ProveedorServicioImpl proveedorServicio;

	@Test
	@Order(1)
	public void crearProveedor() {

		for (int i = 0; i < 10; i++) {
			Proveedor proveedor = Proveedor.builder().nombre("Promix SA" + i).direccion("Monjas").build();
			proveedorServicio.guardarProveedor(proveedor);
			assertNotEquals(proveedor.getId(), 0);
		}
	}

	@Test
	@Order(2)
	public void listarProveedor() {
		Iterable<Proveedor> proveedorIterable = this.proveedorServicio.listarProveedors(0, 10);
		Iterator<Proveedor> proveedorIterator = proveedorIterable.iterator();
		assertTrue(proveedorIterator.hasNext());
		while (proveedorIterator.hasNext()) {
			System.out.println(proveedorIterator.next());
		}
	}

	@Test
	@Order(3)
	public void buscarProveedor() {
		List<Proveedor> listaEncontrados = this.proveedorServicio.buscarProveedorXNombre("Promixyu");
		System.out.println("Encontrados");
		for (Iterator iterator = listaEncontrados.iterator(); iterator.hasNext();) {
			Proveedor proveedor = (Proveedor) iterator.next();
			Hibernate.initialize(proveedor.getProductoList());
			System.out.println(proveedor);
			assertNotEquals(proveedor.getId(), 0);
		}


	}

}
