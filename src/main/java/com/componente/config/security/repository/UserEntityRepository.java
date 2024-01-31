package com.componente.config.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.componente.config.security.entity.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer>  {

	Optional<UserEntity> finByUsernameOrEmail(final String username);
	
	boolean exitsByUsername(String username);
	
	boolean exitsByEmail(String email);
}
