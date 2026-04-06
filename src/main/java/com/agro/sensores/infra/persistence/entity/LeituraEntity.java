package com.agro.sensores.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "leituras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class LeituraEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Relacionamento com sensor(muitos para um)
	@ManyToOne
	@JoinColumn(name = "sensor_id", nullable = false)
	private SensorEntity sensor;
	
	// valor da leitura
	@Column(nullable = false)
	private Double valor;
	
	// data e hora
	@Column(nullable = false)
	private LocalDateTime dataHora;
	
	
	
}
