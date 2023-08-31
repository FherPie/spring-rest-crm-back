package com.componente.factinven.controller;

import java.time.LocalDateTime;
import java.util.Date;
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

import com.componente.factinven.dto.ComprobanteResponse;
import com.componente.factinven.dto.VentaRequest;
import com.componente.factinven.dto.VentaResponse;
import com.componente.factinven.importers.ImporterExcelVenta;
import com.componente.factinven.servicios.impl.VentasServicioImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class VentasController {

	@Autowired
	VentasServicioImpl ventaService;
	

	private final ImporterExcelVenta importerExcelVenta;
	
	//private final StorageService storageService;
	
//	@Autowired
//	public VentaController(StorageService storageService) {
//		this.storageService=storageService;
//	} 
	
	
	@Autowired
	public VentasController(ImporterExcelVenta importerExcelVenta) {
		this.importerExcelVenta=importerExcelVenta;
	} 


	@PutMapping("/venta")
	public ResponseEntity<ComprobanteResponse> actualizar(@RequestBody VentaRequest ventaRequest) {
		return new ResponseEntity<ComprobanteResponse>(ventaService.editarComprobante(ventaRequest), HttpStatus.OK);
	};

	@DeleteMapping("/venta")
	public void delete(@RequestBody VentaRequest ventaRequest) {
		ventaService.eliminarComprobante(ventaRequest);
	};

	@PostMapping("/venta")
	public ResponseEntity<VentaResponse> crear(@RequestBody VentaRequest ventaRequest) {
		return new ResponseEntity<VentaResponse>((VentaResponse) ventaService.guardarComprobante(ventaRequest), HttpStatus.OK);
	};

	@GetMapping("/venta")
	public List<VentaResponse> listarTodos() {
		return ventaService.findAll();
	}
	
	
	@GetMapping("/ventaConCodigoContiene")
	public VentaResponse listarTodosxNombreQueContenga(@RequestParam String codigo) {
		return (VentaResponse) ventaService.buscarComprobanteCodigo(codigo);
	}

	@GetMapping("/venta/{id}")
	public VentaResponse findById(@PathVariable Integer id) {
		return (VentaResponse) ventaService.findById(id);
	}

	@GetMapping("/reporteVentaxCliente")
	public ResponseEntity<List<VentaResponse>> getAllMovimientosByClienteId(@RequestParam(required = false) Integer clienteId,
			@RequestParam(required = true) LocalDateTime startDate, @RequestParam(required = true) LocalDateTime endDate, @RequestParam(required = false) String estado ) {
		try {
			
			List<VentaResponse> listaReporte = ventaService.getAllMovimientoByClienteId(clienteId, startDate, endDate);
			return new ResponseEntity<>(listaReporte, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



	@PostMapping("/venta/uploadFile/{pathName}")
	public String handleFileUpload(@RequestPart(required = true) MultipartFile file, @PathVariable String pathName) {

		importerExcelVenta.LeerExcel(file);
		return "You successfully uploaded " + file.getOriginalFilename() + "!";
	}

}
