package com.componente.factinven.security.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.componente.factinven.security.entities.UserEntity;

@Repository
public interface UserRepository extends IDedRepository<UserEntity> {

    Optional<UserEntity> findByUsername(final String username);
}
