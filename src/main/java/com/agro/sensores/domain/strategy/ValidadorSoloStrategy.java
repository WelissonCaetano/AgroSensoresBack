package com.agro.sensores.domain.strategy;

import org.springframework.stereotype.Component;

import com.agro.sensores.domain.enums.TipoSensor;
import com.agro.sensores.domain.exception.RegraNegocioException;
import com.agro.sensores.domain.model.Leitura;

@Component
public class ValidadorSoloStrategy implements ValidadorSensorStrategy {
	public boolean suporta(TipoSensor tipo) {
		return tipo == TipoSensor.SOLO;
	}
	public void validar(Leitura leitura) {
		if(leitura.getValor() < 0 || leitura.getValor() > 100) {
			throw new RegraNegocioException("Valor de umidade do solo invalida!");
		}
	}
}
 