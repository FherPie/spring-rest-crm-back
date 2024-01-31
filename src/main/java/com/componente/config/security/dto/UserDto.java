package com.componente.config.security.dto;

import java.util.List;

import com.componente.config.security.emuns.RoleEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode()
@ToString()
public class UserDto {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private List<RoleEnum> roles;
}
