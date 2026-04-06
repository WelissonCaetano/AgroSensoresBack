package com.agro.sensores.domain.repository;

import java.util.List;
import java.util.Optional;

import com.agro.sensores.domain.model.Regra;

public interface RegraRepository {
	Regra salvar(Regra regra);
	Optional<Regra> buscarPorId(String id);
	List<Regra> buscarTodos();
	void deletar(String id);
}