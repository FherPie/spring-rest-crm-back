package com.componente.config.security.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TokenDto {

	private String token;
	private LocalDateTime expiry;
	
}
