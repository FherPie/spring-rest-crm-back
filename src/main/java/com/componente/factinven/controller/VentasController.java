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
import com.componente.factinven.responses.ResponseGenerico;
import com.componente.factinven.servicios.impl.VentasServicioImpl;
import com.componente.factinven.utils.ControllersUtils;

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
	
	@PostMapping("/guardarVenta")
	public ResponseEntity<?> crear(@RequestBody VentaResponse ventaRequest) {
        ResponseGenerico<VentaResponse> response = new ResponseGenerico<>();
        VentaResponse dto = ventaService.guardarComprobante(ventaRequest);
        return ControllersUtils.repuestaGenericoExitoObject(response, dto);
      };


	@PutMapping("/actualizarVenta")
	public ResponseEntity<?> actualizar(@RequestBody VentaResponse ventaRequest) {
        ResponseGenerico<VentaResponse> response = new ResponseGenerico<>();
        VentaResponse dto = ventaService.actualizarComprobante(ventaRequest);
        return ControllersUtils.repuestaGenericoExitoObject(response, dto);
		//return new ResponseEntity<VentaResponse>(ventaService.actualizarComprobante(ventaRequest), HttpStatus.OK);
	};

	@DeleteMapping("/borrarVenta/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
	     ResponseGenerico<Boolean> response = new ResponseGenerico<>();
		 Boolean eliminado= ventaService.eliminarComprobante(id);
	     return ControllersUtils.repuestaGenericoExitoObject(response, eliminado);
	};



	@GetMapping("/listarVenta")
	public ResponseEntity<?> listarTodos() {
	     ResponseGenerico<List<VentaResponse>> response = new ResponseGenerico<>();
	     List<VentaResponse> ventasListado= ventaService.findAll();
		 return ControllersUtils.repuestaGenericoExitoList(response, ventasListado);
	}
	
	
	@PostMapping("/addDetalle")
	public ResponseEntity<?> addDetalle(@RequestBody VentaResponse ventaRequest) {
	     ResponseGenerico<VentaResponse> response = new ResponseGenerico<>();
	     VentaResponse venta= ventaService.addDetalle(ventaRequest);
		 return ControllersUtils.repuestaGenericoExitoObject(response, venta);
		//return new ResponseEntity<VentaResponse>((VentaResponse) ventaService.addDetalle(ventaRequest), HttpStatus.OK);
	}
	
	@GetMapping("/editDetalle")
	public ResponseEntity<?> editDetalle(@RequestBody VentaResponse ventaRequest) {
		 ResponseGenerico<VentaResponse> response = new ResponseGenerico<>();
	     VentaResponse venta= ventaService.getVenta();
		 return ControllersUtils.repuestaGenericoExitoObject(response, venta);
		 
		//return new ResponseEntity<VentaResponse>((VentaResponse) ventaService.getVenta(), HttpStatus.OK);
	}

	
	@GetMapping("/deleteDetalle")
	public ResponseEntity<?> deleteDetalle(@RequestBody VentaResponse ventaRequest) {
		 ResponseGenerico<VentaResponse> response = new ResponseGenerico<>();
	     VentaResponse venta= ventaService.getVenta();
		 return ControllersUtils.repuestaGenericoExitoObject(response, venta);
		//return new ResponseEntity<VentaResponse>((VentaResponse) ventaService.getVenta(), HttpStatus.OK);
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
	
	

}
