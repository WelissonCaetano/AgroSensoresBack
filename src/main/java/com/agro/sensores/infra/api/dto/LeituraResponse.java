package com.agro.sensores.infra.api.dto;

import java.time.LocalDateTime;

import com.agro.sensores.domain.model.Leitura;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LeituraResponse {

	private Long id;
	private String sensorId;
	private Double valor;
	private LocalDateTime dataHora;

	public static LeituraResponse fromDomain(Leitura leitura) {
		return new LeituraResponse(
				leitura.getId(),
				leitura.getSensorId() != null ? leitura.getSensorId().getId() : null,
				leitura.getValor(),
				leitura.getDataHora());
	}
}
