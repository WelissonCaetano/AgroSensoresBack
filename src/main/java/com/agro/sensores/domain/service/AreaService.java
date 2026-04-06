package com.agro.sensores.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agro.sensores.domain.exception.RecursoNaoEncontradoException;
import com.agro.sensores.domain.model.Area;
import com.agro.sensores.domain.repository.AreaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AreaService {

	private final AreaRepository areaRepository;

	public Area criar(Area area) {
		return areaRepository.salvar(area);
	}

	public List<Area> listarTodos() {
		return areaRepository.buscarTodos();
	}

	public Area buscarPorId(String id) {
		return areaRepository.buscarPorId(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Área não encontrada"));
	}

	public Area atualizar(String id, Area dadosAtualizados) {
		// Garante que a área existe
		buscarPorId(id);
		Area areaAtualizada = new Area(
				id,
				dadosAtualizados.getNome(),
				dadosAtualizados.getDescricao(),
				dadosAtualizados.getFormato(),
				dadosAtualizados.getTamanho(),
				dadosAtualizados.getLongitude(),
				dadosAtualizados.getLatitude(),
				dadosAtualizados.getLocalizacao(),
				dadosAtualizados.getAtivo());
		return areaRepository.salvar(areaAtualizada);
	}

	public void deletar(String id) {
		// Verifica existência antes de deletar
		buscarPorId(id);
		areaRepository.deletar(id);
	}
}
