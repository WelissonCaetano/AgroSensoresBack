package com.agro.sensores.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agro.sensores.domain.exception.RecursoNaoEncontradoException;
import com.agro.sensores.domain.model.Regra;
import com.agro.sensores.domain.repository.RegraRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegraService {

	private final RegraRepository regraRepository;

	public Regra criar(Regra regra) {
		return regraRepository.salvar(regra);
	}

	public List<Regra> listarTodos() {
		return regraRepository.buscarTodos();
	}

	public Regra buscarPorId(String id) {
		return regraRepository.buscarPorId(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Regra não encontrada"));
	}

	public Regra atualizar(String id, Regra dadosAtualizados) {
		buscarPorId(id);
		Regra regraAtualizada = new Regra(
				id,
				dadosAtualizados.getNome(),
				dadosAtualizados.getSensorId(),
				dadosAtualizados.getValorMinimo(),
				dadosAtualizados.getValorMaximo(),
				dadosAtualizados.getAtivo());
		return regraRepository.salvar(regraAtualizada);
	}

	public void deletar(String id) {
		buscarPorId(id);
		regraRepository.deletar(id);
	}
}