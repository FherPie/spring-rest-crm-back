package com.componente.factinven.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
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
	private Boolean indicadorAfectaciones;
	
	//azules prestaciones requeridas
	private Boolean machaBlanca;
	private Boolean fractura;
	private Boolean caries;
	private Boolean indicadoExtracciones;
	
	
	//rojas prestaciones existentes
	
	private Boolean obturacionAmalgama;
	private Boolean corona;
	private Boolean selladoresFosa;
	private Boolean obturacionResina;

	private Boolean ejecutado;
	private Boolean noejecutado;
	private Boolean enEspera;
	
	
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="client_id")
	private Cliente cliente;

	@ManyToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="pieza_tratamiento",
			joinColumns = {
					@JoinColumn(name="pieza_id")
			},
			inverseJoinColumns ={
					@JoinColumn(name="tratamiento_id")
			})
	private Set<Producto> tratamientosPieza;
	

	public OdontogramaRespuestas(Object object, String string, Integer numero, String nombre2, String string2,
			Object object2) {
		this.id=(Integer) object;
		this.descripcion=string;
		this.pieza=numero; 
		this.nombre=nombre2;
		this.odontogramaPieza=string2;
		this.cliente=(@NotNull Cliente) object2;
	}
	
	

	public OdontogramaRespuestas(Object object, String string, Integer numero, String nombre2, String string2,
			Object object2, Boolean fractura) {
		this.id=(Integer) object;
		this.descripcion=string;
		this.pieza=numero; 
		this.nombre=nombre2;
		this.odontogramaPieza=string2;
		this.cliente=(@NotNull Cliente) object2;
		this.fractura= fractura;
	}
	
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public class PiezaDental{
	     private Integer numero;
	     private String nombre;
		
	     public List<OdontogramaRespuestas> retornarPiezas() {
	    	 List<PiezaDental> listaPieza= new ArrayList<>();
	    	 List<OdontogramaRespuestas> odontogramaRespuestas= new ArrayList<>();
	    	 
	    	 
	    	 
	    	 for (int i = 18; i >= 11; i--) {
				listaPieza.add(new PiezaDental(i, "pieza"+i));
			}
	    	 
	    	 
	       	 for (int i = 21; i <= 28; i++) {
					listaPieza.add(new PiezaDental(i, "pieza"+i));
			}
		    	 
	    	 
	       	 
	       	 for (int i = 55; i >= 51; i--) {
					listaPieza.add(new PiezaDental(i, "pieza"+i));
			 }
	       	 
	     	 for (int i = 61; i <= 65; i++) {
						listaPieza.add(new PiezaDental(i, "pieza"+i));
			}
	     	 
	     	 
	    
	       	 
	       	 for (int i = 85; i >= 81; i--) {
					listaPieza.add(new PiezaDental(i, "pieza"+i));
			 }
	       	 
	     	 for (int i = 71; i <= 75; i++) {
						listaPieza.add(new PiezaDental(i, "pieza"+i));
			}
			    
			    	
	     	 
	       	 
	       	 for (int i = 48; i >= 41; i--) {
					listaPieza.add(new PiezaDental(i, "pieza"+i));
			 }
	       	 
	     	 for (int i = 31; i <= 38; i++) {
						listaPieza.add(new PiezaDental(i, "pieza"+i));
			}
			    
		   	
	    	 for (Iterator iterator = listaPieza.iterator(); iterator.hasNext();) {
				    PiezaDental piezaDental = (PiezaDental) iterator.next();
				    odontogramaRespuestas.add(new OdontogramaRespuestas(null, "", piezaDental.getNumero(), piezaDental.getNombre(), "", null));
				
			}
	    	 return odontogramaRespuestas;
	     }
	     
	}
	
	
	
	
	
}
