package com.componente.factinven.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.componente.factinven.responses.ResponseGenerico;
import com.componente.factinven.security.dtos.UserDTO;
import com.componente.factinven.security.requests.RegistrationRequest;
import com.componente.factinven.security.responses.RegisteredResponse;
import com.componente.factinven.security.services.IUserService;
import com.componente.factinven.security.utils.ControllersUtils;
import com.componente.factinven.security.utils.DataMapperUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "registrations")
@Slf4j
public class RegistrationController {

    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<?> register(
            @Validated @RequestBody final RegistrationRequest registrationRequest) {
        log.info("Received Registration Request C: {} ", registrationRequest.getUsername());
        UserDTO userDTO = DataMapperUtil.convertTo(registrationRequest, UserDTO.class);
        userDTO = userService.createUser(userDTO);
        log.info("Created User C: {} ", userDTO);
        ResponseGenerico<RegisteredResponse> response1 = new ResponseGenerico<>();
        final RegisteredResponse response = DataMapperUtil.convertTo(userDTO, RegisteredResponse.class);
        return ControllersUtils.repuestaGenericoExitoObject(response1, response);
        //return ResponseEntity.ok(response);
    }
}
