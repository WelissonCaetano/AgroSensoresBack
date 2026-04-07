package com.agro.sensores.infra.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlertaRequest {

	@NotBlank
	private String titulo;

	@NotBlank
	private String mensagem;

	@NotBlank
	private String severidade;

	// IDs de relacionamento
	private String sensorId;
	private Long leituraId;

	@NotNull
	private Boolean ativo;
}
