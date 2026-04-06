package com.agro.sensores.infra.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agro.sensores.infra.persistence.entity.AreaEntity;

public interface JpaAreaRepository extends JpaRepository<AreaEntity, String> {

}
