package com.agro.sensores.domain.model;

import com.agro.sensores.domain.exception.ValidacaoException;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Alerta {
	private String id;
	private Sensor sensorId;
	private Leitura leituraId;
	private String titulo;
	private String mensagem;
	private String severidade;
	private Boolean ativo;
	
	public Alerta(
			String id,
			Sensor sensorId,
			Leitura leituraId,
			String titulo,
			String mensagem,
			String severidade,
			Boolean ativo
		) {
		if (titulo == null || titulo.isBlank()) {
			throw new ValidacaoException("Título do alerta é obrigatório");
		}
		if (mensagem == null || mensagem.isBlank()) {
			throw new ValidacaoException("Mensagem do alerta é obrigatória");
		}
		if (severidade == null || severidade.isBlank()) {
			throw new ValidacaoException("Severidade do alerta é obrigatória");
		}
		this.id = id;
		this.sensorId = sensorId;
		this.leituraId = leituraId;
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.severidade = severidade;
		this.ativo = ativo != null ? ativo : Boolean.TRUE;
	}

	public void ativar() {
		this.ativo = true;
	}
	
	public void desativar() {
		this.ativo = false;
	}
	
	public boolean isAtivo() {
		return Boolean.TRUE.equals(ativo);
	}
}
