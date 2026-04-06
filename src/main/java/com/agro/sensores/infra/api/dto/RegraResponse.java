package com.agro.sensores.infra.api.dto;

import com.agro.sensores.domain.model.Regra;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegraResponse {

	private String id;
	private String nome;
	private String sensorId;
	private Float valorMinimo;
	private Float valorMaximo;
	private Boolean ativo;

	public static RegraResponse fromDomain(Regra regra) {
		return new RegraResponse(
				regra.getId(),
				regra.getNome(),
				regra.getSensorId() != null ? regra.getSensorId().getId() : null,
				regra.getValorMinimo(),
				regra.getValorMaximo(),
				regra.isAtivo());
	}
}