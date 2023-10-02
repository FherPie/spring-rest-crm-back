package com.componente.factinven.repositorios;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.componente.factinven.entidades.Producto;

import java.util.List;

import org.springframework.data.domain.Pageable;


public interface ProductRepository extends PagingAndSortingRepository<Producto, Integer> {

    List<Producto> findAllByPrecioUnitario(double pu, Pageable pageable);
}