package com.agro.sensores.domain.repository;

import java.util.List;
import java.util.Optional;

import com.agro.sensores.domain.model.Leitura;

public interface LeituraRepository {
	Leitura salvar(Leitura leitura);
	Optional<Leitura> buscarPorId(Long id);
	List<Leitura> buscarTodos();
	List<Leitura> buscarPorSensor(String sensorId);
	void deletar(Long id);
}
