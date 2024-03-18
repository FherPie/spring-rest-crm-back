package com.componente.factinven.unittests.persona;

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

import com.componente.factinven.entidades.Almacen;
import com.componente.factinven.entidades.Empleado;
import com.componente.factinven.entidades.Persona;
import com.componente.factinven.servicios.impl.AlmacenServicioImpl;
import com.componente.factinven.servicios.impl.EmpleadoServicioImpl;
import com.componente.factinven.servicios.impl.PersonaServicioImpl;

import com.componente.factinven.unittests.persona.UtilPersona;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
public class UnitEmpleadoTests4 {

	// 1 Microincremenrto Repositories

	// Crear Empleado
	@Autowired
	private PersonaServicioImpl personaServicio;
	
	@Autowired
	private EmpleadoServicioImpl empleadoServicio;

	
	@Autowired 
	private AlmacenServicioImpl almacenServicio;

	@Test
	@Order(1)    
	public void crearEmpleado() {
		Persona persona = Persona.builder().nombres("Alejandro").apellidos(UtilPersona.APELLIDO_PARA_PRUEBAS)
				.direccion("Monjas").telefono("099521512").identificacion("1722714796").nombreUsuario("apiedra").build();
		personaServicio.guardarPersona(persona);
		
		Almacen al= Almacen.builder().codigo("001").nombre("Orquideas")
				.direccion("Jose Ortiz y Manuel Escudero").telefono("099521512")
				.build();
		//almacenServicio.guardarAlmacen(al);
		Empleado empleado = Empleado.builder().almacen(al).persona(persona).build();
		empleadoServicio.guardarEmpleado(empleado);
		
		//System.out.println(empleado);
		assertNotEquals(empleado.getId(), 0);
	}
	
	
	@Test
	@Order(2)
	public void listarEmpleado() {
		Persona persona = Persona.builder().nombres("Alejandro").apellidos(UtilPersona.APELLIDO_PARA_PRUEBAS+"2")
				.direccion("Monjas").telefono("099521512").identificacion("1722714778").nombreUsuario("apiedra2").build();
		personaServicio.guardarPersona(persona);
		Almacen al= Almacen.builder().codigo("001").nombre("Orquideas")
				.direccion("Jose Ortiz y Manuel Escudero").telefono("099521512")
				.build();
		///almacenServicio.guardarAlmacen(al);
		Empleado empleado = Empleado.builder().almacen(al).persona(persona).build();
		empleadoServicio.guardarEmpleado(empleado);
		Iterable<Empleado> empleadoIterable = this.empleadoServicio.listarEmpleados(0, 10);
		Iterator<Empleado> empleadoIterator = empleadoIterable.iterator();
		assertTrue(empleadoIterator.hasNext());
		while(empleadoIterator.hasNext()) {
			System.out.println(empleadoIterator.next());
		}
	}
		

	@Test
	@Order(3)
	public void buscarEmpleado() {
		 Empleado encontrado= empleadoServicio.buscarEmpleadoApellido(UtilPersona.APELLIDO_PARA_PRUEBAS+"2");		
		 System.out.println("Empleado Encontrado "+ encontrado );
		 assertNotEquals(encontrado.getId(), 0);
			
	}
	

}
