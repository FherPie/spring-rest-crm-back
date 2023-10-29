package com.componente.factinven.repositorios;

import com.componente.factinven.entidades.DetalleVenta;
import com.componente.factinven.entidades.Venta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepositorio extends JpaRepository<DetalleVenta, Integer>
{


    @Query("select  d from DetalleVenta d where d.venta = ?1")
	List<DetalleVenta>  listaDetallesVenta(Venta venta);

}
