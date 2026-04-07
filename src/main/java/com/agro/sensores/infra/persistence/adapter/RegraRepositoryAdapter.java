package com.agro.sensores.infra.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.agro.sensores.domain.model.Regra;
import com.agro.sensores.domain.model.Sensor;
import com.agro.sensores.domain.repository.RegraRepository;
import com.agro.sensores.infra.persistence.entity.RegraEntity;
import com.agro.sensores.infra.persistence.entity.SensorEntity;
import com.agro.sensores.infra.persistence.repository.JpaRegraRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RegraRepositoryAdapter implements RegraRepository {

	private final JpaRegraRepository jpa;

	@Override
	public Regra salvar(Regra regra) {
		RegraEntity entitySalva = jpa.save(toEntity(regra));
		return new Regra(
				entitySalva.getId(),
				entitySalva.getNome(),
				regra.getSensorId(),
				entitySalva.getValorMinimo(),
				entitySalva.getValorMaximo(),
				entitySalva.isAtivo());
	}

	@Override
	public Optional<Regra> buscarPorId(String id) {
		return jpa.findById(id).map(this::toDomain);
	}

	@Override
	public List<Regra> buscarTodos() {
		return jpa.findAll().stream()
				.map(this::toDomain)
				.collect(Collectors.toList());
	}

	@Override
	public void deletar(String id) {
		jpa.deleteById(id);
	}

	private Regra toDomain(RegraEntity entity) {
		Sensor sensor = new Sensor(
				entity.getSensor().getId(),
				entity.getSensor().getNome(),
				entity.getSensor().getLocalizacao(),
				entity.getSensor().getTipo(),
				entity.getSensor().isAtivo());

		return new Regra(
				entity.getId(),
				entity.getNome(),
				sensor,
				entity.getValorMinimo(),
				entity.getValorMaximo(),
				entity.isAtivo());
	}

	private RegraEntity toEntity(Regra regra) {
		SensorEntity sensorEntity = new SensorEntity();
		sensorEntity.setId(regra.getSensorId().getId());

		RegraEntity entity = new RegraEntity();
		entity.setId(regra.getId());
		entity.setNome(regra.getNome());
		entity.setSensor(sensorEntity);
		entity.setValorMinimo(regra.getValorMinimo());
		entity.setValorMaximo(regra.getValorMaximo());
		entity.setAtivo(regra.isAtivo());
		return entity;
	}
}