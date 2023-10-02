package com.componente.factinven.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.componente.factinven.dto.ProductoRequest;
import com.componente.factinven.dto.ProductoResponse;
import com.componente.factinven.entidades.Producto;
import com.componente.factinven.importers.ImporterExcelProducto;
import com.componente.factinven.servicios.impl.ProductoServicioImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProductoController {

	@Autowired
	ProductoServicioImpl productoService;
	

	private final ImporterExcelProducto importerExcelProducto;
	
	//private final StorageService storageService;
	
//	@Autowired
//	public ProductoController(StorageService storageService) {
//		this.storageService=storageService;
//	} 
	
	
	@Autowired
	public ProductoController(ImporterExcelProducto importerExcelProducto) {
		this.importerExcelProducto=importerExcelProducto;
	} 


	@GetMapping("/resource")
	public Map<String, Object> home() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello World");
		return model;

	}

	@PutMapping("/producto")
	public ResponseEntity<ProductoResponse> actualizar(@RequestBody ProductoRequest productoRequest) {
		return new ResponseEntity<ProductoResponse>(productoService.editarProducto(productoRequest), HttpStatus.OK);
	};

	@DeleteMapping("/producto")
	public void delete(@RequestBody ProductoRequest productoRequest) {
		productoService.eliminarProducto(productoRequest);
	};

	@PostMapping("/producto")
	public ResponseEntity<ProductoResponse> crear(@RequestBody ProductoRequest productoRequest) {
		return new ResponseEntity<ProductoResponse>(productoService.guardarProducto(productoRequest), HttpStatus.OK);
	};

	@GetMapping("/producto")
	public List<ProductoResponse> listarTodos() {
		return productoService.findAll();
	}
	
	
	@GetMapping("/productoConNombreContiene")
	public List<ProductoResponse> listarTodosxNombreQueContenga(@RequestParam String nombre) {
		return productoService.buscarProductoXNombre(nombre);
	}

	@GetMapping("/producto/{id}")
	public ProductoResponse findById(@PathVariable Integer id) {
		return productoService.findById(id);
	}

	@PostMapping("/producto/uploadFile/{pathName}")
	public String handleFileUpload(@RequestPart(required = true) MultipartFile file, @PathVariable String pathName) {

		importerExcelProducto.LeerExcel(file);
		return "You successfully uploaded " + file.getOriginalFilename() + "!";
	}
	
	@GetMapping(params = { "page", "size" })
	public List<ProductoResponse> findPaginated(@RequestParam("page") int page, 
	  @RequestParam("size") int size, UriComponentsBuilder uriBuilder,
	  HttpServletResponse response) throws Exception {
	    Page<ProductoResponse> resultPage = productoService.listarProductos(page, size);
	    if (page > resultPage.getTotalPages()) {
	        throw new Exception();
	    }
		/*
		 * eventPublisher.publishEvent(new
		 * PaginatedResultsRetrievedEvent<ProductoResponse>( ProductoResponse.class,
		 * uriBuilder, response, page, resultPage.getTotalPages(), size));
		 */
	    return resultPage.getContent();
	}

}
