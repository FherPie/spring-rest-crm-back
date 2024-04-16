package com.componente.factinven.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IDedRepository<T> extends JpaRepository<T, Long> {
}