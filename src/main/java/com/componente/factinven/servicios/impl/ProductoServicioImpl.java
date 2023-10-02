package com.componente.factinven.servicios.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.componente.factinven.dto.ProductoRequest;
import com.componente.factinven.dto.ProductoResponse;
import com.componente.factinven.entidades.Producto;
import com.componente.factinven.repositorios.ProductRepository;
import com.componente.factinven.repositorios.ProductoRepositorio;
import com.componente.factinven.servicios.interfaz.IProductoServicio;

@Service
public class ProductoServicioImpl implements IProductoServicio {

	@Autowired
	private ProductoRepositorio productoRepositorio;
	
	@Autowired
	private ProductRepository productoRepository;

	@Override
	@Transactional
	public ProductoResponse guardarProducto(ProductoRequest producto) {
		Producto prdocutoGuardar = new Producto();
		prdocutoGuardar.setNombre(producto.getNombre());
		prdocutoGuardar.setPrecioCompra(producto.getPrecioCompra());
		prdocutoGuardar.setPrecioUnitario(producto.getPrecioUnitario());
		prdocutoGuardar.setStock(producto.getStock());
	     return new ProductoResponse(productoRepositorio.save(prdocutoGuardar));
	}

	@Override
	@Transactional
	public ProductoResponse editarProducto(ProductoRequest Producto) {
         return new ProductoResponse(productoRepositorio.save(new Producto(Producto)));   
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductoResponse> buscarProductoXNombre(String nombre) {
		List<ProductoResponse> listaRetorno = productoRepositorio.findByNombreContainingIgnoreCase(nombre).stream().map(x -> {
			return new ProductoResponse(x);
		}).collect(Collectors.toList());
		return listaRetorno;
	}

	@Override
	@Transactional
	public void eliminarProducto(ProductoRequest productor) {
		productoRepositorio.deleteById(productor.getIdProducto());
	}

	@Override
	@Transactional
	public void borrarProductos() {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(readOnly = true)
	public Page<ProductoResponse> listarProductos(int page, int size) {
		Pageable pages = PageRequest.of(page, size);
		List<ProductoResponse> listaRetorno = productoRepositorio.findAll(pages).stream().map(x -> {
			return new ProductoResponse(x);
		}).collect(Collectors.toList());
		return new PageImpl<ProductoResponse>(listaRetorno);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductoResponse> findAll() {
		List<ProductoResponse> listaRetorno = productoRepositorio.findAll().stream().map(x -> {
			return new ProductoResponse(x);
		}).collect(Collectors.toList());
		return listaRetorno;
	}

	@Override
	@Transactional(readOnly = true)
	public ProductoResponse findById(Integer idProducto) {
		Producto prod = productoRepositorio.findById(idProducto).get();
		if (prod == null) {
			return null;
		}
		return new ProductoResponse(prod);
	}
	
	
	@Transactional
	public void guardarProductoDesdeExcel(Producto producto) {
		Producto prdocutoGuardar = new Producto();
		prdocutoGuardar.setNombre(producto.getNombre());
		prdocutoGuardar.setPrecioCompra(producto.getPrecioCompra());
		prdocutoGuardar.setPrecioUnitario(producto.getPrecioUnitario());
		prdocutoGuardar.setStock(producto.getStock());
	    productoRepositorio.save(prdocutoGuardar);
	}

	@Override
	public List<ProductoResponse> findAllP() {
	
		
		
		return null;
	}

//	@Override
//	public void guardarProducto(Producto Producto) {
//
//		productoRepositorio.save(Producto);
//	}
//
//
//	@Override
//	public void eliminarProducto(Producto Producto) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	@Override
//	public Page<Producto> listarProductos(int page, int size) {
//		Pageable pages = PageRequest.of(page, size);
//		return this.productoRepositorio.findAll(pages);
//	}
//
//
//	@Override
//	public void editarProducto(Producto Producto) {
//		// TODO Auto-generated method stub
//		this.productoRepositorio.save(Producto);
//	}
//
//
//	@Override
//	public void borrarProductos() {
//		// TODO Auto-generated method stub
//         this.productoRepositorio.deleteAll();		
//	}
//
//
//	@Override
//	public List<Producto> buscarProductoXNombre(String nombre) {
//	 return this.productoRepositorio.findByNombreContainingIgnoreCase(nombre);
//	}

}
