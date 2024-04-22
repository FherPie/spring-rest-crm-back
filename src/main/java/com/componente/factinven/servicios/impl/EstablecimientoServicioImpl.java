package com.componente.factinven.servicios.impl;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.componente.factinven.dto.EstablecimientoDto;
import com.componente.factinven.entidades.Establecimiento;
import com.componente.factinven.entidades.ImageModel;
import com.componente.factinven.mappers.EstablecimientoMapper;
import com.componente.factinven.repositorios.EstablecimientoRepositorio;
import com.componente.factinven.utils.ServiciosUtils;



@Service
@Transactional
public class EstablecimientoServicioImpl  {


	@Autowired
	EstablecimientoRepositorio establecimientoRespositorio;
	
	@Autowired
	private EstablecimientoMapper establecimientoMapper;
	

	public EstablecimientoDto guardar(EstablecimientoDto establecimientoDto, MultipartFile[] files ) {
		
		Establecimiento establecimiento =establecimientoMapper.toEntity(establecimientoDto);
        try {
			Set<ImageModel> images= uploadImage(files);
			establecimiento.setImageEstablishment(images);
		} catch (Exception e) {
            System.out.println(e.getMessage());
		}
		return establecimientoMapper.toDto(establecimientoRespositorio.save(establecimiento));
	}
	
	
    private  Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
   	 Set<ImageModel> imageModels= new HashSet<>();
   	 for(MultipartFile file: multipartFiles) {
				ImageModel imgm= new ImageModel(file.getName(), file.getContentType(), file.getBytes());
				imageModels.add(imgm);
   	 }
   	 return imageModels;
    }
	


	public EstablecimientoDto actualizar(EstablecimientoDto establecimientoDto) {
		Establecimiento establecimiento =establecimientoMapper.toEntity(establecimientoDto);
		return establecimientoMapper.toDto(establecimientoRespositorio.save(establecimiento));
	}


	public List<EstablecimientoDto> findAll() {
		return establecimientoMapper.toDto(establecimientoRespositorio.findAll());
	}

	
	public EstablecimientoDto findByUserId(Integer id) {
		id= ServiciosUtils.userLoggedId();
		return establecimientoMapper.toDto(establecimientoRespositorio.findByCreatedBy(id));
	}
	
	
	public boolean eliminar(Integer id) {
         establecimientoRespositorio.deleteById(id);
         return true;
	}

}
