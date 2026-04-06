package com.agro.sensores.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agro.sensores.domain.exception.RecursoNaoEncontradoException;
import com.agro.sensores.domain.model.Sensor;
import com.agro.sensores.domain.repository.SensorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SensorService {

	private final SensorRepository sensorRepository;

	public Sensor criar(Sensor sensor) {
		return sensorRepository.salvar(sensor);
	}

	public List<Sensor> listarTodos() {
		return sensorRepository.buscarTodos();
	}

	public Sensor buscarPorId(String id) {
		return sensorRepository.buscarPorId(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Sensor não encontrado"));
	}

	public Sensor atualizar(String id, Sensor dadosAtualizados) {
		buscarPorId(id);
		Sensor sensorAtualizado = new Sensor(
				id,
				dadosAtualizados.getNome(),
				dadosAtualizados.getLocalizacao(),
				dadosAtualizados.getTipo(),
				dadosAtualizados.getAtivo());
		return sensorRepository.salvar(sensorAtualizado);
	}

	public void deletar(String id) {
		buscarPorId(id);
		sensorRepository.deletar(id);
	}
}
