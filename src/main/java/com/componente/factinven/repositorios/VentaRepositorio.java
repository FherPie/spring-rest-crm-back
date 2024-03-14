package com.componente.factinven.repositorios;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.componente.factinven.entidades.Comprobante;
import com.componente.factinven.entidades.Venta;

@Repository
public interface VentaRepositorio extends JpaRepository<Venta, Integer> {


	public Comprobante findByCodigo(String codigo);
	
	
    @Query("SELECT "
            + " (venta) from Venta venta "
            + "WHERE "
            + "cliente.id = :clienteId  and fechayHora BETWEEN :startDate AND :endDate order by venta.id desc")
    List<Venta> fecthVentaBetweenDatesAndClientID(@Param("startDate")LocalDateTime startDate,@Param("endDate")LocalDateTime endDate, Integer clienteId);
    
    @Query("SELECT "
            + " (venta) from Venta venta "
            + "WHERE "
            + "cliente.id = :clienteId  and fechayHora BETWEEN :startDate AND :endDate order  by venta.id desc")
    List<Venta> fecthVentaOrderByDate(@Param("startDate")LocalDateTime startDate,@Param("endDate")LocalDateTime endDate, Integer clienteId);


    public List<Venta> findAllByOrderByIdDesc();

}
