package com.agro.sensores.infra.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegraRequest {

	@NotBlank
	private String nome;

	@NotBlank
	private String sensorId;

	@NotNull
	private Float valorMinimo;

	@NotNull
	private Float valorMaximo;

	@NotNull
	private Boolean ativo;
}