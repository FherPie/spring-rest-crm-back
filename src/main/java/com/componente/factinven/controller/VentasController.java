package com.componente.factinven.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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


	@PutMapping("/actualizarVenta")
	public ResponseEntity<VentaResponse> actualizar(@RequestBody VentaResponse ventaRequest) {
		return new ResponseEntity<VentaResponse>(ventaService.actualizarComprobante(ventaRequest), HttpStatus.OK);
	};

	@DeleteMapping("/borrarVenta/{id}")
	public void delete(@PathVariable Integer id) {
		ventaService.eliminarComprobante(id);
	};

	@PostMapping("/guardarVenta")
	public ResponseEntity<VentaResponse> crear(@RequestBody VentaResponse ventaRequest) {
		return new ResponseEntity<VentaResponse>((VentaResponse) ventaService.guardarComprobante(ventaRequest), HttpStatus.OK);
	};

	@GetMapping("/listarVenta")
	public List<VentaResponse> listarTodos() {
		return ventaService.findAll();
	}
	
	
	@GetMapping("/ventaConCodigoContiene")
	public VentaResponse listarTodosxNombreQueContenga(@RequestParam String codigo) {
		return (VentaResponse) ventaService.buscarComprobanteCodigo(codigo);
	}

	@GetMapping("/getByIdVenta/{id}")
	public ResponseEntity<VentaResponse> findById(@PathVariable Integer id) {
		return new ResponseEntity<VentaResponse>((VentaResponse) ventaService.findById(id), HttpStatus.OK);
	}

	@GetMapping("/reporteVentaxFechas")
	public ResponseEntity<List<VentaResponse>> getAllMovimientosByClienteId(@RequestParam(required = false) Integer clienteId,
			@RequestParam(required = true) String startDate,
			@RequestParam(required = true) String endDate,
			@RequestParam(required = false) String estado ) {
		try {  
		    String europeanDatePattern = "yyyy-MM-dd HH:mm:ss";
		    DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
		    LocalDateTime start = europeanDateFormatter.parse(startDate, LocalDateTime::from);
		    LocalDateTime end = europeanDateFormatter.parse(endDate, LocalDateTime::from);
		    List<VentaResponse> listaReporte = ventaService.getAllMovimientoByClienteId(clienteId, start, end);
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
	
	
	@GetMapping("/getVenta")
	public ResponseEntity<VentaResponse> getVenta() {
		return new ResponseEntity<VentaResponse>((VentaResponse) ventaService.getVenta(), HttpStatus.OK);
	}
	
	
	@PostMapping("/addDetalle")
	public ResponseEntity<VentaResponse> addDetalle(@RequestBody VentaResponse ventaRequest) {
		return new ResponseEntity<VentaResponse>((VentaResponse) ventaService.addDetalle(ventaRequest), HttpStatus.OK);
	}
	
	@GetMapping("/editDetalle")
	public ResponseEntity<VentaResponse> editDetalle(@RequestBody VentaResponse ventaRequest) {
		return new ResponseEntity<VentaResponse>((VentaResponse) ventaService.getVenta(), HttpStatus.OK);
	}

	
	@GetMapping("/deleteDetalle")
	public ResponseEntity<VentaResponse> deleteDetalle(@RequestBody VentaResponse ventaRequest) {
		return new ResponseEntity<VentaResponse>((VentaResponse) ventaService.getVenta(), HttpStatus.OK);
	}
}
