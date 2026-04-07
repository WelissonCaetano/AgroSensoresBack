package com.agro.sensores.infra.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.agro.sensores.domain.model.Area;
import com.agro.sensores.domain.repository.AreaRepository;
import com.agro.sensores.infra.persistence.entity.AreaEntity;
import com.agro.sensores.infra.persistence.repository.JpaAreaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AreaRepositoryAdapter implements AreaRepository {

	private final JpaAreaRepository jpa;

	@Override
	public Area salvar(Area area) {
		return toDomain(jpa.save(toEntity(area)));
	}

	@Override
	public Optional<Area> buscarPorId(String id) {
		return jpa.findById(id).map(this::toDomain);
	}

	@Override
	public List<Area> buscarTodos() {
		return jpa.findAll().stream()
				.map(this::toDomain)
				.collect(Collectors.toList());
	}

	@Override
	public void deletar(String id) {
		jpa.deleteById(id);
	}

	private Area toDomain(AreaEntity entity) {
		return new Area(
				entity.getId(),
				entity.getNome(),
				entity.getDescricao(),
				entity.getFormato(),
				entity.getTamanho(),
				entity.getLongitude(),
				entity.getLatitude(),
				entity.getLocalizacao(),
				entity.isAtivo());
	}

	private AreaEntity toEntity(Area area) {
		return new AreaEntity(
				area.getId(),
				area.getNome(),
				area.getDescricao(),
				area.getFormato(),
				area.getTamanho(),
				area.getLongitude(),
				area.getLatitude(),
				area.getLocalizacao(),
				area.isAtivo());
	}
}
