package com.componente.factinven.security.services;

import java.util.List;
import java.util.Optional;

import com.componente.factinven.security.dtos.UserDTO;

public interface IUserService {

    UserDTO createUser(final UserDTO userDTO);
    UserDTO updateUser(final UserDTO userDTO);
    Optional<UserDTO> getByUsername(final String username);
    List<UserDTO> getAll();
    boolean inactivateUser(Long id);
    boolean activateUser(Long id);
}
