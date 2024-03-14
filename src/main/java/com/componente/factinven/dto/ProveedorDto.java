package com.componente.factinven.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ProveedorDto  {

	private static final long serialVersionUID = 1028409979983251377L;
	private List<DetalleVentaRequest> itemsFactura= new ArrayList<>();
	
	
	
	@Data
	public static class DetalleVentaRequest{
		
		
		private int id;
		private int unidad;
		private Integer productoId;
		private double precioUnitario;
		private double descuentoUnitario;
		private double precioporDetalle;
		private int numeroDetalle;
		private double numeroItems;
		
		
		
		
		
		public DetalleVentaRequest(int id, int unidad, Integer idProducto, double precioUnitario,
				double descuentoUnitario, double precioporDetalle, int numeroDetalle, int numeroItems) {
			super();
			this.id = id;
			this.unidad = unidad;
			this.productoId = idProducto;
			this.precioUnitario = precioUnitario;
			this.descuentoUnitario = descuentoUnitario;
			this.precioporDetalle = precioporDetalle;
			this.numeroDetalle = numeroDetalle;
			this.numeroItems = numeroItems;
		}




		public DetalleVentaRequest() {
	
		}
		
		

		
	}

}
