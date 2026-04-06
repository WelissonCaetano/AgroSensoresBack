package com.agro.sensores.infra.api.dto;

import com.agro.sensores.domain.enums.TipoSensor;
import com.agro.sensores.domain.model.Sensor;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SensorResponse {

	private String id;
	private String nome;
	private String localizacao;
	private TipoSensor tipo;
	private Boolean ativo;

	public static SensorResponse fromDomain(Sensor sensor) {
		return new SensorResponse(
				sensor.getId(),
				sensor.getNome(),
				sensor.getLocalizacao(),
				sensor.getTipo(),
				sensor.isAtivo());
	}
}
