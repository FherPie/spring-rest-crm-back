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

import com.componente.factinven.dto.ProductoDto;
import com.componente.factinven.entidades.Producto;
import com.componente.factinven.mappers.ProductoMapper;
import com.componente.factinven.repositorios.ProductRepository;
import com.componente.factinven.repositorios.ProductoRepositorio;
import com.componente.factinven.servicios.interfaz.IProductoServicio;

@Service
public class ProductoServicioImpl implements IProductoServicio {

	@Autowired
	private ProductoRepositorio productoRepositorio;
	
	@Autowired
	private ProductRepository productoRepository;
	
	@Autowired
	private ProductoMapper productoMapper;

	@Override
	@Transactional
	public ProductoDto guardarProducto(ProductoDto productoDto) {
		Producto prdocutoGuardar = productoMapper.toEntity(productoDto);
	     return productoMapper.toDto(productoRepositorio.save(prdocutoGuardar));
	}

	@Override
	@Transactional
	public ProductoDto editarProducto(ProductoDto productoDto) {
		Producto prdocutoGuardar = productoMapper.toEntity(productoDto);
	     return productoMapper.toDto(productoRepositorio.save(prdocutoGuardar)); 
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductoDto> buscarProductoXNombre(String nombre) {
		List<ProductoDto> listaRetorno = productoRepositorio.findByNombreContainingIgnoreCase(nombre).stream().map(x -> {
			return new ProductoDto(x);
		}).collect(Collectors.toList());
		return listaRetorno;
	}

	@Override
	@Transactional
	public boolean eliminarProducto(Integer id) {
		productoRepositorio.deleteById(id);
		return true;
	}

	@Override
	@Transactional
	public void borrarProductos() {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(readOnly = true)
	public Page<ProductoDto> listarProductos(int page, int size) {
		Pageable pages = PageRequest.of(page, size);
		List<ProductoDto> listaRetorno = productoRepositorio.findAll(pages).stream().map(x -> {
			return new ProductoDto(x);
		}).collect(Collectors.toList());
		return new PageImpl<ProductoDto>(listaRetorno);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductoDto> findAll() {
		List<ProductoDto> listaRetorno = productoMapper.toDto(productoRepositorio.findAll());
//		.stream().map(x -> {
//			return new ProductoDto(x);
//		}).collect(Collectors.toList());
		return listaRetorno;
	}

	@Override
	@Transactional(readOnly = true)
	public ProductoDto findById(Integer idProducto) {
		Producto prod = productoRepositorio.findById(idProducto).get();        
		return productoMapper.toDto(prod);
	}
	
	
	@Transactional
	public void guardarProductoDesdeExcel(Producto producto) {
		Producto prdocutoGuardar = new Producto();
		prdocutoGuardar.setNombre(producto.getNombre());
		prdocutoGuardar.setPrecioUnitario(producto.getPrecioUnitario());
	    productoRepositorio.save(prdocutoGuardar);
	}

	@Override
	public List<ProductoDto> findAllP() {
	
		
		
		return null;
	}

	@Override
	public List<ProductoDto> findProductoOdonto() {
		List<ProductoDto> listaRetorno = productoMapper.toDto(productoRepositorio.findByServdeOdontogramaTrue());
		return listaRetorno;
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
