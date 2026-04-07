package com.agro.sensores.infra.api.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeituraRequest {

	@NotBlank
	private String sensorId;

	@NotNull
	private Double valor;

	@NotNull
	private LocalDateTime dataHora;
}
