package com.agro.sensores.infra.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.agro.sensores.domain.model.Alerta;
import com.agro.sensores.domain.model.Leitura;
import com.agro.sensores.domain.model.Sensor;
import com.agro.sensores.domain.repository.AlertaRepository;
import com.agro.sensores.infra.persistence.entity.AlertaEntity;
import com.agro.sensores.infra.persistence.entity.LeituraEntity;
import com.agro.sensores.infra.persistence.entity.SensorEntity;
import com.agro.sensores.infra.persistence.repository.JpaAlertaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AlertaRepositoryAdapter implements AlertaRepository {

	private final JpaAlertaRepository jpa;

	@Override
	public Alerta salvar(Alerta alerta) {
		AlertaEntity entitySalva = jpa.save(toEntity(alerta));
		return new Alerta(
				entitySalva.getId(),
				alerta.getSensorId(),
				alerta.getLeituraId(),
				entitySalva.getTitulo(),
				entitySalva.getMensagem(),
				entitySalva.getSeveridade(),
				entitySalva.isAtivo());
	}

	@Override
	public Optional<Alerta> buscarPorId(String id) {
		return jpa.findById(id).map(this::toDomain);
	}

	@Override
	public List<Alerta> buscarTodos() {
		return jpa.findAll().stream()
				.map(this::toDomain)
				.collect(Collectors.toList());
	}

	@Override
	public void deletar(String id) {
		jpa.deleteById(id);
	}

	private Alerta toDomain(AlertaEntity entity) {
		Sensor sensor = new Sensor(
				entity.getSensor().getId(),
				entity.getSensor().getNome(),
				entity.getSensor().getLocalizacao(),
				entity.getSensor().getTipo(),
				entity.getSensor().isAtivo());
		
		Leitura leitura = null;
		if (entity.getLeitura() != null) {
			Sensor sensorLeitura = new Sensor(
					entity.getLeitura().getSensor().getId(),
					entity.getLeitura().getSensor().getNome(),
					entity.getLeitura().getSensor().getLocalizacao(),
					entity.getLeitura().getSensor().getTipo(),
					entity.getLeitura().getSensor().isAtivo());
			leitura = new Leitura(
					entity.getLeitura().getId(),
					sensorLeitura,
					entity.getLeitura().getValor(),
					entity.getLeitura().getDataHora());
		}
		
		return new Alerta(
				entity.getId(),
				sensor,
				leitura,
				entity.getTitulo(),
				entity.getMensagem(),
				entity.getSeveridade(),
				entity.isAtivo());
	}

	private AlertaEntity toEntity(Alerta alerta) {
		SensorEntity sensorEntity = new SensorEntity();
		sensorEntity.setId(alerta.getSensorId().getId());
		
		AlertaEntity entity = new AlertaEntity();
		entity.setId(alerta.getId());
		entity.setTitulo(alerta.getTitulo());
		entity.setMensagem(alerta.getMensagem());
		entity.setSeveridade(alerta.getSeveridade());
		entity.setSensor(sensorEntity);
		if (alerta.getLeituraId() != null) {
			LeituraEntity leituraEntity = new LeituraEntity();
			leituraEntity.setId(alerta.getLeituraId().getId());
			entity.setLeitura(leituraEntity);
		}
		entity.setAtivo(alerta.isAtivo());
		return entity;
	}
}
