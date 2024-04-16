package com.componente.factinven.security.responses;

import com.componente.factinven.security.enums.AccountStatus;
import com.componente.factinven.security.enums.RoleAuthority;

import lombok.Data;

@Data
public class RegisteredResponse implements IResponse{

    private String firstName;
    private String lastName;
    private RoleAuthority role;
    private AccountStatus status;
    private String username;
}
