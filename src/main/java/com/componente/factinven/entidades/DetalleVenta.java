package com.componente.factinven.entidades;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class DetalleVenta extends DetalleComprobante{

	

	@NotNull	
	@ManyToOne(fetch = FetchType.LAZY)
	private Venta venta;

	@PrePersist
	public void intentoRegistroDetalle() {
		
	}

}
