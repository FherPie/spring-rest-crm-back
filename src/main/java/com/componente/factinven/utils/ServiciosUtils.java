package com.componente.factinven.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.componente.factinven.security.dtos.UserPrincipal;
import com.componente.factinven.security.facades.IAuthenticationFacade;

@Component
public class ServiciosUtils {

	@Autowired
	private static IAuthenticationFacade authenticationFacade;

    

    public static Integer userLoggedId(){
        Authentication authentication = authenticationFacade.getAuthentication();
	    Integer id=((UserPrincipal) authentication.getPrincipal()).getUserDTO().getId();
	    return id;
    }




}
