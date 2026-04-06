package com.agro.sensores.domain.model;

import com.agro.sensores.domain.enums.TipoSensor;
import com.agro.sensores.domain.exception.ValidacaoException;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Sensor {
	private String id;
	private String nome;
	private String localizacao;
	private TipoSensor tipo;
	private Boolean ativo;

	
	
	public Sensor(
			String id,
			String nome,
			String localizacao,
			TipoSensor tipo,
			Boolean ativo
			) {
		
		if(nome == null ||nome.isBlank()) {
			throw new ValidacaoException("Nome do sensor é Obrigatoria");
		}
		if(localizacao == null ||localizacao.isBlank()) {
			throw new ValidacaoException("Localização do sensor é Obrigatoria");
		}
		if(tipo == null) {
			throw new ValidacaoException("Tipo do sensor é Obrigatoria");
		}
		this.id = id;
		this.nome = nome;
		this.localizacao = localizacao;
		this.tipo = tipo;
		this.ativo = ativo;
	}
	
	public void ativar() {
		this.ativo = true;
	}
	public void destivar() {
		this.ativo = false;
	}
	public boolean isAtivo() {
		return ativo;
	}
	
}
