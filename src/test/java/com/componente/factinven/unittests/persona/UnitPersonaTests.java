package com.componente.factinven.unittests.persona;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Iterator;

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

import com.componente.factinven.entidades.Persona;
import com.componente.factinven.servicios.impl.PersonaServicioImpl;
import com.componente.factinven.unittests.persona.UtilPersona;

//
//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
//@SpringBootTest
//@ActiveProfiles("test")
//@TestMethodOrder(OrderAnnotation.class)
public class UnitPersonaTests {

	// https://github.com/tiwasi/inventario/tree/master/AplicacionWebInventario/src/java/com/inventario/entidades
	// http://openaccess.uoc.edu/webapps/o2/bitstream/10609/65705/5/jgernunTFM0617memoria.pdf
	// https://www.incanatoit.com/2019/08/base-datos-compras-ventas-sql-server.html
	// http://opac.pucv.cl/pucv_txt/txt-3000/UCD3129_01.pdf
	// https://virtual.uptc.edu.co/ova/lenguaje-sql/modelo.pdf

	// 1 Microincremenrto Repositories

	// Crear Personas
	@Autowired
	private PersonaServicioImpl personaServicio;

	@Test
	@Order(1)    
	public void crearPersona() {
		Persona persona = Persona.builder().nombres("Alejandro").apellidos(UtilPersona.APELLIDO_PARA_PRUEBAS)
				.direccion("Monjas").telefono("099521512").build();
		personaServicio.guardarPersona(persona);
		assertNotEquals(persona.getId(), 0);
	}

	// Editar Personas

	@Test
	@Order(2) 
	public void editarPersona() {
		Persona persona = Persona.builder().nombres("Alejandro").apellidos(UtilPersona.APELLIDO_PARA_PRUEBAS)
				.direccion("Monjas").telefono("099521512").build();
		personaServicio.editarPersona(persona);
		assertNotEquals(persona.getId(), 0);
	}

	// Listar Personas con paginacion

	@Test
	@Order(3)
	public void listarPersona() {
		Iterable<Persona> personaIterable = this.personaServicio.listarPersonas(0, 10);
		Iterator<Persona> personaIterator = personaIterable.iterator();
		assertTrue(personaIterator.hasNext());
	}

	// Borrar Personas uno por uno
	@Test
	@Order(4)
	public void borrarPersonas() {
		this.personaServicio.borrarPersonas();
		Iterable<Persona> personaIterable = this.personaServicio.listarPersonas(0, 10);
		Iterator<Persona> personaIterator = personaIterable.iterator();
		assertFalse(personaIterator.hasNext());
	}

	// Crear Almacenes

	// Editar Almacenes

	// Listar Almacenes con paginacion

	// Borrar Almacenes uno por uno
	

	// Crear Producto

	// Editar Producto

	// Listar Productos con paginacion

	// Borrar Producto uno por uno
	

	// Crear Cliente

	// Editar Cliente

	// Listar Cliente con paginacion

	// Borrar Cliente uno por uno
	

	// Crear Factura

	// Editar Factura

	// Listar Facturas con Paginacion

	// Borrar Factura uno por uno
	

	// Crear Detalle Factura

	// Editar Detalle Factura

	// Listar Detalle Facturas con Paginacion

	// Borrar Detalle Factura uno por uno
	

	// Crear Detalle Empleado

	// Editar Detalle Empleado

	// Listar Detalle Empleado con Paginacion

	// Borrar Detalle Empleado uno por uno
	
	

	// 2 Microincremento

	// Crear CategoriaProducto

	// Editar CategoriaProducto

	// Listar CategoriaProducto con paginacion

	// Borrar CategoriaProducto uno por uno
	

	// Crear FormaDePago

	// Editar FormaDePago

	// Listar FormaPago con paginacion

	// Borrar FormaPago uno por uno
	

	// Crear Proveedor

	// Editar Proveedor

	// Listar Proveedor con paginacion

	// Borrar Proveedor uno por uno
	

	// Crear Entradas

	// Editar Entradas

	// Listar Entradas con Paginacion

	// Borrar Entradas Factura uno por uno
	

	// 4 Microincremento Microservicios de Catalogos

	// Crear Marca de Productos

	// Editar Marca de Producto

	// Listar Marca de Producto con Paginacion

	// Borrar Marca Productos uno por uno
	

	// Crear Categoria de Productos

	// Editar Categorias de Producto

	// Listar Categorias de Producto con Paginacion

	// Borrar Categorias Productos uno por uno

}
