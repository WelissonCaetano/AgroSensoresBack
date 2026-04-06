package com.agro.sensores.domain.repository;

import java.util.List;
import java.util.Optional;

import com.agro.sensores.domain.model.Alerta;

public interface AlertaRepository {
	Alerta salvar(Alerta alerta);
	Optional<Alerta> buscarPorId(String id);
	List<Alerta> buscarTodos();
	void deletar(String id);
}
