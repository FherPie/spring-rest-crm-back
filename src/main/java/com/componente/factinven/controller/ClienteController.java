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

import com.componente.factinven.dto.ClienteDto;
import com.componente.factinven.dto.EntradaDto;
import com.componente.factinven.dto.VentaResponse;
import com.componente.factinven.importers.ImporterExcelCliente;
import com.componente.factinven.responses.ResponseGenerico;
import com.componente.factinven.servicios.impl.ClienteServicioImpl;
import com.componente.factinven.utils.ControllersUtils;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ClienteController {

	@Autowired
	ClienteServicioImpl clienteService;
	

	private final ImporterExcelCliente importerExcelCliente;

	@Autowired
	public ClienteController(ImporterExcelCliente importerExcelCliente) {
		this.importerExcelCliente=importerExcelCliente;
	} 
	
	@PostMapping("/cliente")
	public ResponseEntity<?> crear(@RequestBody ClienteDto clienteRequest) throws Exception {
        ResponseGenerico<ClienteDto> response = new ResponseGenerico<>();
        ClienteDto dto = clienteService.guardarCliente(clienteRequest);
        return ControllersUtils.repuestaGenericoExitoObject(response, dto);
	};
	

	@PostMapping("/crearEntradaCliente")
	public ResponseEntity<?> crear(@RequestBody EntradaDto entradaDto){
		EntradaDto dto= clienteService.crearEntradaCliente(entradaDto);
		return new ResponseEntity<EntradaDto>(dto, HttpStatus.OK);
	}
	
	
	@GetMapping("/listarPagosCliente/{id}")
	public ResponseEntity<?> listarPagosdeClientes(@PathVariable Integer id){
		  ResponseGenerico<List<EntradaDto>> response = new ResponseGenerico<>();
		  List<EntradaDto> ventasListado= clienteService.listarPagostClientes(id);
		return ControllersUtils.repuestaGenericoExitoList(response, ventasListado);
	}; 
	

	@PutMapping("/cliente")
	public ResponseEntity<?> actualizar(@RequestBody ClienteDto clienteRequest) throws Exception {
	     ResponseGenerico<ClienteDto> response = new ResponseGenerico<>();
	      ClienteDto dto = clienteService.editarCliente(clienteRequest);
	     return ControllersUtils.repuestaGenericoExitoObject(response, dto);
		
	};

	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
	       ResponseGenerico<Boolean> response = new ResponseGenerico<>();
		   Boolean eliminado= clienteService.eliminarCliente(id);
	       return ControllersUtils.repuestaGenericoExitoObject(response, eliminado);
	};


	@GetMapping("/cliente")
	public List<ClienteDto> listarTodos() {
		return clienteService.findAll();
	}
	
	
	
	@GetMapping("/clienteConNombreContiene")
	public List<ClienteDto> listarTodosViaNombre(@RequestParam String apellidos) {
		return clienteService.findAllPorApellidoContains(apellidos);
	}

	@GetMapping("/cliente/{id}")
	public ClienteDto findById(@PathVariable Integer id) {
		return clienteService.findById(id);
	}

	@PostMapping("/cliente/uploadFile/{pathName}")
	public String handleFileUpload(@RequestPart(required = true) MultipartFile file, @PathVariable String pathName) {
		importerExcelCliente.LeerExcel(file);
		return "You successfully uploaded " + file.getOriginalFilename() + "!";
	}
	
	@GetMapping("/presupuestosCliente/{clienteId}")
	public List<VentaResponse> presupuestosByClienteId(@PathVariable Integer clienteId){
	   return clienteService.listarPresupuestoCliente(clienteId);
	}
	 
	
	
//	@GetMapping("/obtenerPreguntasCliente")
//	public ClienteDto instanciarCliente() {
//		return clienteService.instanciarPreguntasCliente();
//	}

}
