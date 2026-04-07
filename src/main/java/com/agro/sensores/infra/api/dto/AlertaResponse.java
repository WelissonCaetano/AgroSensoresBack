package com.agro.sensores.infra.api.dto;

import com.agro.sensores.domain.model.Alerta;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AlertaResponse {

	private String id;
	private String titulo;
	private String mensagem;
	private String severidade;
	private String sensorId;
	private Long leituraId;
	private Boolean ativo;

	public static AlertaResponse fromDomain(Alerta alerta) {
		return new AlertaResponse(
				alerta.getId(),
				alerta.getTitulo(),
				alerta.getMensagem(),
				alerta.getSeveridade(),
				alerta.getSensorId() != null ? alerta.getSensorId().getId() : null,
				alerta.getLeituraId() != null ? alerta.getLeituraId().getId() : null,
				alerta.isAtivo());
	}
}
