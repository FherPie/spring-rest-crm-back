package com.componente.factinven.entidades;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
//import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Establecimiento extends AuditedEntity implements Serializable {

	private static final long serialVersionUID = -3238892727484412795L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nombre;
	private String identificacion;
	private String direccion;
	private String ciudad;
	private String telefono;
	private String telefono2;
	private String codPostal;
	private String email;
	private String webSite;
	
	@ManyToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="establishment_images",
	  joinColumns = {
			  @JoinColumn(name="establishment_id")
	  }, 
	  inverseJoinColumns ={
		  @JoinColumn(name="image_id")	  
	  })
	private Set<ImageModel> imageEstablishment;
}
