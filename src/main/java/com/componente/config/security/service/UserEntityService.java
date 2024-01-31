package com.componente.config.security.service;

import com.componente.config.security.dto.UserDto;
import java.util.Optional;
import java.util.List;

public interface UserEntityService {
	
	UserDto createUser(final UserDto userDto);
	Optional<UserDto> getByUsernameOrEmail(final String username);
	List<UserDto> getAll();
	

}
