package com.componente.factinven.servicios.interfaz;

import java.util.List;

import org.springframework.data.domain.Page;

import com.componente.factinven.dto.ProductoDto;

public interface IProductoServicio {

	public abstract  ProductoDto guardarProducto(ProductoDto producto);
	
	public abstract  ProductoDto editarProducto(ProductoDto producto);
	
	public abstract  List<ProductoDto> buscarProductoXNombre(String nombre);
	
	public ProductoDto findById(Integer idProducto );
	
	public abstract  boolean eliminarProducto(Integer id);
	
	public abstract  void borrarProductos();

	public abstract  Page<ProductoDto> listarProductos(int page, int size);
	
	public List<ProductoDto> findAll();
	
	public List<ProductoDto> findAllP();

	public List<ProductoDto> findProductoOdonto();
}
