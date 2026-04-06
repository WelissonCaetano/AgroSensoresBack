package com.agro.sensores.infra.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alertas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AlertaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(nullable = false)
	private String titulo;
	
	@Column(nullable = false, length = 2000)
	private String mensagem;
	
	@Column(nullable = false)
	private String severidade;
	
	// relacionamento com Sensor
	@ManyToOne
	@JoinColumn(name = "sensor_id", nullable = false)
	private SensorEntity sensor;
	
	// relacionamento com Leitura (muitos alertas para uma leitura)
	@ManyToOne
	@JoinColumn(name = "leitura_id")
	private LeituraEntity leitura;
	
	@Column(nullable = false)
	private boolean ativo;
}
