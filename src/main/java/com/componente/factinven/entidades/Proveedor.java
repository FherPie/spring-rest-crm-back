package com.componente.factinven.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class Proveedor extends AuditedEntity implements Serializable {

	private static final long serialVersionUID = -3238892727484412795L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nombre;
	private String direccion;
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idProveedor")
//	private List<OrdenCompra> ordencompraList;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedor", fetch = FetchType.LAZY, orphanRemoval = true)
	private Collection<Producto> productoList= new ArrayList<>();

	

}
