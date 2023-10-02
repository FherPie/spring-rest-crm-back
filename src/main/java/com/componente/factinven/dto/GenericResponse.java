package com.componente.factinven.dto;

import java.util.List;

public class GenericResponse {
	
	private Object object;
	private List<Object> listado;
	private int numeroRegistros;
	
	
	public GenericResponse() {
		super();
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public List<Object> getListado() {
		return listado;
	}
	public void setListado(List<Object> listado) {
		this.listado = listado;
	}
	public int getNumeroRegistros() {
		return numeroRegistros;
	}
	public void setNumeroRegistros(int numeroRegistros) {
		this.numeroRegistros = numeroRegistros;
	}
	
	

}
