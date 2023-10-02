package com.componente.factinven.servicios.interfaz;

import java.util.List;

import org.springframework.data.domain.Page;

import com.componente.factinven.dto.ProductoRequest;
import com.componente.factinven.dto.ProductoResponse;

public interface IProductoServicio {

	public abstract  ProductoResponse guardarProducto(ProductoRequest producto);
	
	public abstract  ProductoResponse editarProducto(ProductoRequest producto);
	
	public abstract  List<ProductoResponse> buscarProductoXNombre(String nombre);
	
	public ProductoResponse findById(Integer idProducto );
	
	public abstract  void eliminarProducto(ProductoRequest producto);
	
	public abstract  void borrarProductos();

	public abstract  Page<ProductoResponse> listarProductos(int page, int size);
	
	public List<ProductoResponse> findAll();
	
	public List<ProductoResponse> findAllP();
}
