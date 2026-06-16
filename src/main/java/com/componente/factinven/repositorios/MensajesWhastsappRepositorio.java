package com.componente.factinven.repositorios;

import com.componente.factinven.entidades.Mascotas;
import com.componente.factinven.entidades.MensajesWhatsapp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajesWhastsappRepositorio extends JpaRepository<MensajesWhatsapp, Integer> {

}
