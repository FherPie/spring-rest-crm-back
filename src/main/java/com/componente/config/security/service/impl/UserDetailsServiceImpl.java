package com.componente.config.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.componente.config.security.dto.UserDto;
import com.componente.config.security.dto.UserPrincipal;
import com.componente.config.security.service.UserEntityService;

@Service
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserEntityService userEntityService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final UserDto userDto= userEntityService.getByUsernameOrEmail(username).orElseThrow(()-> new UsernameNotFoundException("Usuario not found"));
		return new UserPrincipal(userDto);
	}

}
