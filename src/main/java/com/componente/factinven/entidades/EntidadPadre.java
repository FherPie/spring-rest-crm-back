package com.componente.factinven.entidades;

import java.time.LocalDateTime;

import javax.persistence.Column;

public class EntidadPadre {
	
	@Column(name="created_date")
	private LocalDateTime createdDate;
	
	@Column(name="created_by")
	private Persona createdBy;
	
	@Column(name="updated_date")
	private LocalDateTime updatedDate;
	
	@Column(name="updated_by")
	private Persona updatedBy;

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Persona getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Persona createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Persona getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Persona updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	
	
	
}
