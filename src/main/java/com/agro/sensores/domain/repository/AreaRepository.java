package com.agro.sensores.domain.repository;

import java.util.List;
import java.util.Optional;

import com.agro.sensores.domain.model.Area;

public interface AreaRepository {
	Area salvar(Area area);
	Optional<Area> buscarPorId(String id);
	List<Area> buscarTodos();
	void deletar(String id);
}
