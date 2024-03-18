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


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
public class UnitPersonaTests2 {

	// 1 Microincremenrto Repositories

	// Crear Personas
	@Autowired
	private PersonaServicioImpl personaServicio;

	@Test
	@Order(1)    
	public void crearPersona() {
		Persona persona = Persona.builder().nombres("Alejandro").apellidos(UtilPersona.APELLIDO_PARA_PRUEBAS)
				.direccion("Monjas").telefono("099521512").identificacion("1722714795").nombreUsuario("apiedra").build();
		personaServicio.guardarPersona(persona);
		assertNotEquals(persona.getId(), 0);
	}

	// Editar Personas

	@Test
	@Order(2) 
	public void crearPersonaIdentificacionx2() {
		Persona persona = Persona.builder().nombres("Alejandro").apellidos(UtilPersona.APELLIDO_PARA_PRUEBAS)
				.direccion("Monjas").telefono("099521512").identificacion("1722714795").nombreUsuario("apiedra").build();
		personaServicio.guardarPersona(persona);
	
	}



}
