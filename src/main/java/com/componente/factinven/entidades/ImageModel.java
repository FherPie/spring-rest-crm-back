package com.componente.factinven.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="image_model")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ImageModel {
	
      @Id
      @GeneratedValue(strategy=GenerationType.AUTO)
	  private Long id;
	  private String name;
	  private String type;
	  @Column(length = 50000000)
	  private byte[] picByte;
	  
	  
	  
	public ImageModel(String name, String type, byte[] picByte) {
		super();
		this.name = name;
		this.type = type;
		this.picByte = picByte;
	}
	  
	  
	
}
