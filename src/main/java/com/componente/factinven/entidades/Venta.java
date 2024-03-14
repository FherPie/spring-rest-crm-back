package com.componente.factinven.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Venta extends Comprobante {
	
	private static final long serialVersionUID = -158044866183889173L;
	//@NotEmpty
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="venta", orphanRemoval = true)
	private List<DetalleVenta> detallesVenta;

}
