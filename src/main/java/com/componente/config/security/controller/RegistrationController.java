package com.componente.config.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.componente.config.security.dto.UserDto;
import com.componente.config.security.service.UserEntityService;
import com.componente.factinven.ResponseDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value="/api")
@Slf4j
public class RegistrationController {

	 @Autowired
	 private UserEntityService userEntityService;
	 
	@PostMapping
	@RequestMapping(value="/registration")
	public ResponseEntity<ResponseDto> register (
			@Validated @RequestBody final UserDto userDto)
	{
		ResponseDto response= new ResponseDto();
		UserDto userDt0= userEntityService.createUser(userDto);
		response.setObjeto(userDt0);
		return ResponseEntity.ok(response);	
	}
}
