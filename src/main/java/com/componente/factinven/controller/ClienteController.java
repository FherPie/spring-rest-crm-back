package com.componente.factinven.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.componente.factinven.dto.ClienteRequest;
import com.componente.factinven.dto.ClienteResponse;
import com.componente.factinven.importers.ImporterExcelCliente;
import com.componente.factinven.servicios.interfaz.IClienteServicio;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ClienteController {

	@Autowired
	IClienteServicio clienteService;
	

	private final ImporterExcelCliente importerExcelCliente;

	@Autowired
	public ClienteController(ImporterExcelCliente importerExcelCliente) {
		this.importerExcelCliente=importerExcelCliente;
	} 

	@PutMapping("/cliente")
	public ResponseEntity<ClienteResponse> actualizar(@RequestBody ClienteRequest clienteRequest) {
		return new ResponseEntity<ClienteResponse>(clienteService.editarCliente(clienteRequest), HttpStatus.OK);
	};

	@DeleteMapping("/cliente")
	public void delete(@RequestBody ClienteRequest clienteRequest) {
		clienteService.eliminarCliente(clienteRequest);
	};

	@PostMapping("/cliente")
	public ResponseEntity<ClienteResponse> crear(@RequestBody ClienteRequest clienteRequest) {
		return new ResponseEntity<ClienteResponse>(clienteService.guardarCliente(clienteRequest), HttpStatus.OK);
	};

	@GetMapping("/cliente")
	public List<ClienteResponse> listarTodos() {
		return clienteService.findAll();
	}
	
	@GetMapping("/clienteConNombreContiene")
	public List<ClienteResponse> listarTodosViaNombre(@RequestParam String apellidos) {
		return clienteService.findAllPorApellidoContains(apellidos);
	}

	@GetMapping("/cliente/{id}")
	public ClienteResponse findById(@PathVariable Integer id) {
		return clienteService.findById(id);
	}

	@PostMapping("/cliente/uploadFile/{pathName}")
	public String handleFileUpload(@RequestPart(required = true) MultipartFile file, @PathVariable String pathName) {
		importerExcelCliente.LeerExcel(file);
		return "You successfully uploaded " + file.getOriginalFilename() + "!";
	}

}
