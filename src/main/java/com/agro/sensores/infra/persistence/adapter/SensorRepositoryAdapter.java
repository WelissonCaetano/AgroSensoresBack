package com.agro.sensores.infra.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.agro.sensores.domain.model.Sensor;
import com.agro.sensores.domain.repository.SensorRepository;
import com.agro.sensores.infra.persistence.entity.SensorEntity;
import com.agro.sensores.infra.persistence.repository.JpaSensorRepository;

import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class SensorRepositoryAdapter implements SensorRepository {
	private final JpaSensorRepository jpa;
	

	public Sensor salvar (Sensor sensor) {
	 return toDomain(jpa.save(toEntity(sensor)));
	}
	
	public Optional<Sensor> buscarPorId(String id){
		return jpa.findById(id).map(entity -> toDomain((SensorEntity) entity));
	}
	
	public List<Sensor> buscarTodos(){
		return jpa.findAll().stream()
				.map(this::toDomain)
				.collect(Collectors.toList());
	} 
		
	public void deletar(String id) {
		jpa.deleteById(id);
	}
	
	public Sensor toDomain(SensorEntity entity) {
		return new Sensor(
				entity.getId(),
				entity.getNome(),
				entity.getLocalizacao(),
				entity.getTipo(),
				entity.isAtivo()
				
				);
	}
	
	private SensorEntity toEntity (Sensor sensor) {
		return new SensorEntity(
				sensor.getId(),
				sensor.getNome(),
				sensor.getLocalizacao(),
				sensor.getTipo(),
				sensor.getAtivo()
				);
	}
}

