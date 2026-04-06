package com.agro.sensores.infra.persistence.repository;

import com.agro.sensores.infra.persistence.entity.LeituraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaLeituraRepository extends JpaRepository<LeituraEntity, String>{
	// buscar leituras por sensor
	List<LeituraEntity>  findAllBySensor_id(String sensorId);
	
	// Optional<List<LeituraEntity>>
}
