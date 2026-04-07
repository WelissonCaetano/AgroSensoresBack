package com.agro.sensores.infra.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agro.sensores.infra.persistence.entity.RegraEntity;

public interface JpaRegraRepository extends JpaRepository<RegraEntity, String> {
}