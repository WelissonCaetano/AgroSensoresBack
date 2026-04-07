package com.agro.sensores.infra.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agro.sensores.infra.persistence.entity.AlertaEntity;

public interface JpaAlertaRepository extends JpaRepository<AlertaEntity, String> {

}
