package com.agro.sensores.infra.persistence.adapter;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import com.agro.sensores.domain.model.Leitura;
import com.agro.sensores.domain.model.Sensor;
import com.agro.sensores.domain.repository.LeituraRepository;
import com.agro.sensores.infra.persistence.entity.LeituraEntity;
import com.agro.sensores.infra.persistence.entity.SensorEntity;
import com.agro.sensores.infra.persistence.repository.JpaLeituraRepository;
import com.agro.sensores.infra.persistence.repository.JpaSensorRepository;

import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class LeituraRepositoryAdapter implements LeituraRepository {

	private final JpaLeituraRepository jpa;
	private final JpaSensorRepository sensorJpa;
	
	public Leitura salvar (Leitura leitura) {
		LeituraEntity entitySalva = jpa.save(toEntity(leitura));
		// return jpa.findById(entitySalva.getId())
		// 		.map(this::toDomain)
		// 		.orElseThrow(() -> new IllegalStateException("Leitura salva não encontrada"));
		return new Leitura(
				entitySalva.getId(),
				leitura.getSensorId(),
				entitySalva.getValor(),
				entitySalva.getDataHora());
	}
	
	public List<Leitura> buscarPorSensor(String sensorId){
		return jpa.findAllBySensor_id(sensorId).stream()
				.map(this::toDomain)
				.collect(Collectors.toList());
	}

	public Optional<Leitura> buscarPorId(Long id) {
		return jpa.findById(id).map(this::toDomain);
	}

	public List<Leitura> buscarTodos() {
		return jpa.findAll().stream()
				.map(this::toDomain)
				.collect(Collectors.toList());
	}

	public void deletar(Long id) {
		jpa.deleteById(id);
	}
	
	private Leitura toDomain(LeituraEntity entity) {
		SensorEntity sensorEntity = entity.getSensor();
		if (sensorEntity != null && (sensorEntity.getNome() == null || sensorEntity.getTipo() == null)) {
			sensorEntity = sensorJpa.findById(sensorEntity.getId())
					.orElseThrow(() -> new IllegalStateException("Sensor da leitura não encontrado"));
		}

		Sensor sensorDomain = new Sensor(
				sensorEntity.getId(),
				sensorEntity.getNome(),
				sensorEntity.getLocalizacao(),
				sensorEntity.getTipo(),
				sensorEntity.isAtivo()
				);
		
		return new Leitura(
				entity.getId(),
				sensorDomain,
				entity.getValor(),
				entity.getDataHora()
				);
	}
	
	private LeituraEntity toEntity(Leitura leitura) {
		SensorEntity sensorEntity = new SensorEntity();
		sensorEntity.setId(leitura.getSensorId().getId());
		return new LeituraEntity(
				leitura.getId(),
				sensorEntity,
				leitura.getValor(),
				leitura.getDataHora()
				);
	}
}
