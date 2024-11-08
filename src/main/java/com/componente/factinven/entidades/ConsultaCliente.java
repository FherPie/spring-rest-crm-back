package com.componente.factinven.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConsultaCliente implements Serializable {

	private static final long serialVersionUID = 1869080843648181632L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Date fecha;
	private String motivo;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="client_id")
	private Cliente cliente;
}
