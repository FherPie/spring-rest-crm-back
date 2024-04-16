package com.componente.factinven.security.responses;

import java.time.LocalDateTime;

import com.componente.factinven.security.dtos.UserDTO;

import lombok.Data;

@Data
public class AuthenticatedTokenResponse implements IResponse {

    private String token;
    private LocalDateTime expiry;
    private UserDTO user;

}
