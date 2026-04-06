package com.agro.sensores.domain.model;

import com.agro.sensores.domain.exception.ValidacaoException;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Area {
	private String id;
	private String nome;
	private String descricao;
	private String formato;
	private Integer tamanho;
	private Float longitude;
	private Float latitude;
	private String localizacao;
	private Boolean ativo;
	
	public Area(
			String id,
			String nome,
			String descricao,
			String formato,
			Integer tamanho,
			Float longitude,
			Float latitude,
			String localizacao,
			Boolean ativo
		) {
		if (nome == null || nome.isBlank()) {
			throw new ValidacaoException("Nome da área é obrigatório");
		}
		if (localizacao == null || localizacao.isBlank()) {
			throw new ValidacaoException("Localização da área é obrigatória");
		}
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.formato = formato;
		this.tamanho = tamanho;
		this.longitude = longitude;
		this.latitude = latitude;
		this.localizacao = localizacao;
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
