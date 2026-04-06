package com.agro.sensores.domain.model;

import com.agro.sensores.domain.exception.ValidacaoException;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Regra {

	private String id;
	private String nome;
	private Sensor sensorId;
	private Float valorMinimo;
	private Float valorMaximo;
	private Boolean ativo;

	
	
	public Regra(
			String id,
			String nome,
			Sensor sensorId,
			Float valorMinimo,
			Float valorMaximo,
			Boolean ativo
			) {
		
		if(nome == null ||nome.isBlank()) {
			throw new ValidacaoException("Nome da regra é Obrigatoria");
		}
		if(sensorId == null) {
			throw new ValidacaoException("Sensor da regra é Obrigatorio");
		}
		if(valorMinimo == null || valorMaximo == null) {
			throw new ValidacaoException("Valores minimo e maximo sao obrigatorios");
		}
		this.id = id;
		this.nome = nome;
		this.sensorId = sensorId;
		this.valorMinimo = valorMinimo;
        this.valorMaximo = valorMaximo;
		this.ativo = ativo != null ? ativo : Boolean.TRUE;
	}
	
	public void ativar() {
		this.ativo = true;
	}
	public void destivar() {
		this.ativo = false;
	}
	public boolean isAtivo() {
		return Boolean.TRUE.equals(ativo);
	}
	
}
