package com.componente.factinven.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.componente.factinven.dto.ProductoDto;
import com.componente.factinven.importers.ImporterExcelProducto;
import com.componente.factinven.responses.ResponseGenerico;
import com.componente.factinven.servicios.impl.ProductoServicioImpl;
import com.componente.factinven.utils.ControllersUtils;

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


	@PutMapping("/producto")
	public ResponseEntity<?> actualizar(@RequestBody ProductoDto productoRequest) {
		 ResponseGenerico<ProductoDto> response = new ResponseGenerico<>();
	     ProductoDto dto = productoService.guardarProducto(productoRequest);
	     return ControllersUtils.repuestaGenericoExitoObject(response, dto);
	};

	@DeleteMapping("/producto/{id}")
	public  ResponseEntity<?> delete(@PathVariable Integer id) {
	    ResponseGenerico<Boolean> response = new ResponseGenerico<>();
	     Boolean eliminado= productoService.eliminarProducto(id);
		return ControllersUtils.repuestaGenericoExitoObject(response, eliminado);
	};

	@PostMapping("/producto")
	public ResponseEntity<?> crear(@RequestBody ProductoDto productoRequest) {
        ResponseGenerico<ProductoDto> response = new ResponseGenerico<>();
        ProductoDto dto = productoService.guardarProducto(productoRequest);
        return ControllersUtils.repuestaGenericoExitoObject(response, dto);
	};

	@GetMapping("/producto")
	public List<ProductoDto> listarTodos() {
		return productoService.findAll();
	}
	
	
	@GetMapping("/productoConNombreContiene")
	public List<ProductoDto> listarTodosxNombreQueContenga(@RequestParam String nombre) {
		return productoService.buscarProductoXNombre(nombre);
	}

	@GetMapping("/producto/{id}")
	public ProductoDto findById(@PathVariable Integer id) {
		return productoService.findById(id);
	}

	@PostMapping("/producto/uploadFile/{pathName}")
	public String handleFileUpload(@RequestPart(required = true) MultipartFile file, @PathVariable String pathName) {

		importerExcelProducto.LeerExcel(file);
		return "You successfully uploaded " + file.getOriginalFilename() + "!";
	}
	
	@GetMapping(params = { "page", "size" })
	public List<ProductoDto> findPaginated(@RequestParam("page") int page, 
	  @RequestParam("size") int size, UriComponentsBuilder uriBuilder,
	  HttpServletResponse response) throws Exception {
	    Page<ProductoDto> resultPage = productoService.listarProductos(page, size);
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


	@GetMapping("/productoOdonto")
	public List<ProductoDto> listarProductoOdonto() {
		return productoService.findProductoOdonto();
	}

}
