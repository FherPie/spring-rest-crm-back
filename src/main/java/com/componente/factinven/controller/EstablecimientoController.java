package com.componente.factinven.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.componente.factinven.dto.EstablecimientoDto;
import com.componente.factinven.responses.ResponseGenerico;
import com.componente.factinven.servicios.impl.EstablecimientoServicioImpl;
import com.componente.factinven.utils.ControllersUtils;

@RestController
@RequestMapping("/api")
public class EstablecimientoController {

	@Autowired
	EstablecimientoServicioImpl establecimientoService;
	
	
	@PostMapping(value="/establishmentuser", consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> guardar(@RequestPart("establecimientoDto") EstablecimientoDto establecimientoDto,
			@RequestPart("imageFile") MultipartFile[] file ) {
        ResponseGenerico<EstablecimientoDto> response = new ResponseGenerico<>();
        EstablecimientoDto dto = establecimientoService.guardar(establecimientoDto, file);
        return ControllersUtils.repuestaGenericoExitoObject(response, dto);
      };

     
	@PutMapping("/establishment")
	public ResponseEntity<?> actualizar(@RequestBody EstablecimientoDto establecimientoDto) {
        ResponseGenerico<EstablecimientoDto> response = new ResponseGenerico<>();
        EstablecimientoDto dto = establecimientoService.actualizar(establecimientoDto);
        return ControllersUtils.repuestaGenericoExitoObject(response, dto);
	};

	@DeleteMapping("/establishment/{id}")
	public ResponseEntity<?> borrar(@PathVariable Integer id) {
	     ResponseGenerico<Boolean> response = new ResponseGenerico<>();
		 Boolean eliminado= establecimientoService.eliminar(id);
	     return ControllersUtils.repuestaGenericoExitoObject(response, eliminado);
	};



	@GetMapping("/establishment")
	public ResponseEntity<?> listar() {
	     ResponseGenerico<List<EstablecimientoDto>> response = new ResponseGenerico<>();
	     List<EstablecimientoDto> ventasListado= establecimientoService.findAll();
		 return ControllersUtils.repuestaGenericoExitoList(response, ventasListado);
	}
	
	
	

	@GetMapping("/establishmentuser")
	public ResponseEntity<?> findById(@RequestHeader(name = "userId", required = false) Integer userId) {
        ResponseGenerico<EstablecimientoDto> response = new ResponseGenerico<>();
       System.out.println("Hello"+userId);
        EstablecimientoDto dto = establecimientoService.findByUserId(userId);
        return ControllersUtils.repuestaGenericoExitoObject(response, dto);		
	}



	

}
