package com.agro.sensores.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agro.sensores.domain.exception.RecursoNaoEncontradoException;
import com.agro.sensores.domain.model.Alerta;
import com.agro.sensores.domain.repository.AlertaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlertaService {

	private final AlertaRepository alertaRepository;

	public Alerta criar(Alerta alerta) {
		return alertaRepository.salvar(alerta);
	}

	public List<Alerta> listarTodos() {
		return alertaRepository.buscarTodos();
	}

	public Alerta buscarPorId(String id) {
		return alertaRepository.buscarPorId(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Alerta não encontrado"));
	}

	public Alerta atualizar(String id, Alerta dadosAtualizados) {
		// Garante que o alerta existe
		buscarPorId(id);
		Alerta alertaAtualizado = new Alerta(
				id,
				dadosAtualizados.getSensorId(),
				dadosAtualizados.getLeituraId(),
				dadosAtualizados.getTitulo(),
				dadosAtualizados.getMensagem(),
				dadosAtualizados.getSeveridade(),
				dadosAtualizados.getAtivo());
		return alertaRepository.salvar(alertaAtualizado);
	}

	public void deletar(String id) {
		// Verifica existência antes de deletar
		buscarPorId(id);
		alertaRepository.deletar(id);
	}
}
