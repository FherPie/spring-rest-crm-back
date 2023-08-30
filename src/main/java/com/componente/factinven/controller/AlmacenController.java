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

import com.componente.factinven.dto.VentaResponse;
import com.componente.factinven.entidades.Almacen;
import com.componente.factinven.importers.ImporterExcelVenta;
import com.componente.factinven.servicios.impl.AlmacenServicioImpl;

import lombok.Data;

@CrossOrigin(origins = "http://localhost:4200")
@RestController                                                                                                            
@RequestMapping("/api/mantenimiento")
public class AlmacenController {

	@Autowired
	AlmacenServicioImpl almacenServicio;
	

	private final ImporterExcelVenta importerExcelVenta;
	
	@Autowired
	public AlmacenController(ImporterExcelVenta importerExcelVenta) {
		this.importerExcelVenta=importerExcelVenta;
	} 


	@PutMapping("/almacen")
	public ResponseEntity<AlmacenResponse> actualizar(@RequestBody AlmacenRequest almacenRequest) {
		return new ResponseEntity<AlmacenResponse>(almacenServicio.editarAlmacen(almacenRequest), HttpStatus.OK);
	};

	@DeleteMapping("/almacen")
	public void delete(@RequestBody AlmacenRequest almacenRequest) {
		almacenServicio.eliminarAlmacen(almacenRequest);
	};

	@PostMapping("/venta")
	public ResponseEntity<AlmacenResponse> crear(@RequestBody AlmacenRequest almacenRequest) {
		return new ResponseEntity<AlmacenResponse>(almacenServicio.guardarAlmacen(almacenRequest), HttpStatus.OK);
	};

	@GetMapping("/venta")
	public List<VentaResponse> listarTodos() {
		//entaService.findAll();
		return null;
	}
	
	
	@GetMapping("/almacenConNombreContiene")
	public AlmacenResponse listarTodosxNombreQueContenga(@RequestParam String nombre) {
		return (AlmacenResponse) almacenServicio.buscarAlmacenNombre(nombre);
	}

	@GetMapping("/almacen/{id}")
	public AlmacenResponse findById(@PathVariable Integer id) {
		return (AlmacenResponse) almacenServicio.findById(id);
	}

	@PostMapping("/venta/uploadFile/{pathName}")
	public String handleFileUpload(@RequestPart(required = true) MultipartFile file, @PathVariable String pathName) {

		importerExcelVenta.LeerExcel(file);
		return "You successfully uploaded " + file.getOriginalFilename() + "!";
	}
	
	@Data
	public static class AlmacenRequest {
		private int id;
		private String codigo;
		private String nombre;
		private String direccion;
		private String telefono;
		private int empleadoaCargoId;
	}
	
	@Data
    public static class AlmacenResponse {
		
		private int id;
		private String codigo;
		private String nombre;
		private String direccion;
		private String telefono;
		private String empleadoaCargoName;
		
		
		public AlmacenResponse(Almacen almacen) {
			
			this.id = almacen.getId();
			this.codigo = almacen.getCodigo();
			this.nombre = almacen.getNombre();
			this.direccion = almacen.getDireccion();
			this.telefono = almacen.getTelefono();
			this.empleadoaCargoName=almacen.getEmpleadoaCargo()!=null? almacen.getEmpleadoaCargo().getPersona().getNombres() :null;
			
		}
		
		
		
	}

}
