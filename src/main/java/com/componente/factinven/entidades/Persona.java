package com.componente.factinven.entidades;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.UniqueElements;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(uniqueConstraints=
{@UniqueConstraint(columnNames = "identificacion", name = "IdentificacionUnica"),
@UniqueConstraint(columnNames = "nombreUsuario", name = "NombreUsuarioUnico")})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Persona implements Serializable {
	
	private static final long serialVersionUID = -346900551499497684L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    @NotBlank
	private String nombres;
    @NotBlank
	private String apellidos;
    @NotNull
	private String direccion;
    @NotBlank
    private String telefono;
    private String telefono2;
    @Email
    private String email;
	public Date fechaNacimiento;
    private String nombreUsuario;
    private String pin;
    private String identificacion;
}
