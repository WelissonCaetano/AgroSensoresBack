package com.agro.sensores.infra.api.dto;

import com.agro.sensores.domain.enums.TipoSensor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorRequest {

	@NotBlank
	private String nome;

	@NotBlank
	private String localizacao;

	@NotNull
	private TipoSensor tipo;

	@NotNull
	private Boolean ativo;
}
