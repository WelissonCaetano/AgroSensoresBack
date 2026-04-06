package com.agro.sensores.domain.model;

import java.time.LocalDateTime;

import com.agro.sensores.domain.exception.ValidacaoException;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Leitura {

	private Long id;
	private Sensor sensorId;
	private Double valor;
	private LocalDateTime dataHora;

	
		
		public Leitura(
				Long id,
				Sensor sensorId,
				 Double valor,
				 LocalDateTime dataHora
				) {
		
			if(sensorId == null) {
				throw new ValidacaoException("SensorId é Obrigatorio");
				
			}
			if(valor == null ) {
				throw new ValidacaoException("Valor é Obrigatorio");
				
			}
			
			if(dataHora == null ) {
				throw new ValidacaoException("Data/Hora é Obrigatorio");
				
			}
			
			this.id = id;
			this.sensorId = sensorId;
			this.valor = valor;
			this.dataHora = dataHora;
	}
		
		public boolean isRecente() {
			return dataHora.isAfter(LocalDateTime.now().minusHours(1));
		}
		public boolean isValorValido(double min, double max) {
			return valor >= min && valor <= max;
		}
}
