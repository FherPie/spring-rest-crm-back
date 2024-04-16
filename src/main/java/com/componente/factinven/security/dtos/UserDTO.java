package com.componente.factinven.security.dtos;

import com.componente.factinven.security.enums.AccountStatus;
import com.componente.factinven.security.enums.RoleAuthority;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserDTO extends IDedDTO {

    private String firstName;
    private String lastName;
    private RoleAuthority role;
    private AccountStatus status;
    private String username;
    private String password;
    
    
    
}
