package com.agro.sensores.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agro.sensores.domain.exception.RecursoNaoEncontradoException;
import com.agro.sensores.domain.model.Leitura;
import com.agro.sensores.domain.model.Sensor;
import com.agro.sensores.domain.repository.LeituraRepository;
import com.agro.sensores.domain.repository.SensorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeituraService {

	private final LeituraRepository leituraRepository;
	private final SensorRepository sensorRepository;

	public Leitura criar(String sensorId, Leitura leitura) {
		Sensor sensor = sensorRepository.buscarPorId(sensorId)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Sensor não encontrado"));

		Leitura novaLeitura = new Leitura(
				null,
				sensor,
				leitura.getValor(),
				leitura.getDataHora(),
				leitura.getLocalizacao());

		return leituraRepository.salvar(novaLeitura);
	}

	public List<Leitura> listarTodos() {
		return leituraRepository.buscarTodos();
	}

	public Leitura buscarPorId(Long id) {
		return leituraRepository.buscarPorId(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Leitura não encontrada"));
	}

	public List<Leitura> buscarPorSensor(String sensorId) {
		return leituraRepository.buscarPorSensor(sensorId);
	}

	public void deletar(Long id) {
		buscarPorId(id);
		leituraRepository.deletar(id);
	}
}
