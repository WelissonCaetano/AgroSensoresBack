package com.agro.sensores.domain.strategy;

import com.agro.sensores.domain.enums.TipoSensor;
import com.agro.sensores.domain.model.Leitura;

public interface ValidadorSensorStrategy {
	boolean suporta(TipoSensor Tipo);
	
	void validar(Leitura leitura);
}
