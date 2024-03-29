package com.componente.factinven.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private String direccion;
	private String ciudad;
	private String telefono;
	private String telefono2;
	private String codPostal;
	private String email;
	private String webSite;
}
