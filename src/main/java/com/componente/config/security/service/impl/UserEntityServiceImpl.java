package com.componente.config.security.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.componente.config.security.dto.UserDto;
import com.componente.config.security.entity.UserEntity;
import com.componente.config.security.repository.UserEntityRepository;
import com.componente.config.security.service.UserEntityService;
import com.componente.config.security.utils.DataMapperUtil;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class UserEntityServiceImpl implements UserEntityService {
	
	@Autowired
	private UserEntityRepository userEntityRepository;
	

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDto createUser(UserDto userDto) {  
		log.info("Crear Usuarios con Detalles de usuario: {}", userDto);
		UserEntity userEntity= DataMapperUtil.convertTo(userDto, UserEntity.class);
		userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
		userEntity= userEntityRepository.save(userEntity);
		log.info("Saved User Entity in Service Layer : {}", userEntity);
		return DataMapperUtil.convertTo(userEntity, UserDto.class);
	}

	@Override
	public Optional<UserDto> getByUsernameOrEmail(String username) {
		final Optional<UserEntity> userEntiOptional= userEntityRepository.finByUsernameOrEmail(username);
		if(userEntiOptional.isEmpty()) {
			return Optional.empty();
		}
		final UserEntity userEntity= userEntiOptional.get();
		return Optional.of(DataMapperUtil.convertTo(userEntity, UserDto.class));
	}

	@Override
	public List<UserDto> getAll() {
		final List<UserDto> userDtos= new ArrayList<>();
		userEntityRepository.findAll().forEach(userEntity->{
			userDtos.add(DataMapperUtil.convertTo(userEntity, UserDto.class));
		});
		return userDtos;
	}

}
