package com.agro.sensores.infra.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agro.sensores.infra.persistence.entity.UsuarioEntity;

public interface JpaUsuarioRepository extends JpaRepository<UsuarioEntity, String> {

	Optional<UsuarioEntity> findByLogin(String login);
	 
}
