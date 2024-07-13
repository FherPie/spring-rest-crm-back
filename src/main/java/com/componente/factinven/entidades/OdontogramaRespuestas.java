package com.componente.factinven.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

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
public class OdontogramaRespuestas implements Serializable {

	private static final long serialVersionUID = 1869080843648181632L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String descripcion;
	private Integer pieza;
	private String nombre;
	private String odontogramaPieza;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="client_id")
	private Cliente cliente;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public class PiezaDental{
	     private Integer numero;
	     private String nombre;
		
	     public List<OdontogramaRespuestas> retornarPiezas() {
	    	 List<PiezaDental> listaPieza= new ArrayList<>();
	    	 List<OdontogramaRespuestas> odontogramaRespuestas= new ArrayList<>();
	    	 
	    	 for (int i = 11; i <= 48; i++) {
				listaPieza.add(new PiezaDental(i, "pieza"+i));
				if(i==18) {
					i=20;
				}else if(i==28) {
					i=30;
				} else if(i==38) {
					i=40;
				}
			}
	    	 
	    	 for (int i = 51; i <= 85; i++) {
					listaPieza.add(new PiezaDental(i, "pieza"+i));
					if(i==55) {
						i=60;
					}else if(i==65) {
						i=70;
					} else if(i==75) {
						i=80;
					}
			}  	
	    	 for (Iterator iterator = listaPieza.iterator(); iterator.hasNext();) {
				    PiezaDental piezaDental = (PiezaDental) iterator.next();
				    odontogramaRespuestas.add(new OdontogramaRespuestas(null, "", piezaDental.getNumero(), piezaDental.getNombre(), "", null));
				
			}
	    	 return odontogramaRespuestas;
	     }
	     
	}
	
	
	
	
	
}
