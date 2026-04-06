package com.agro.sensores.infra.persistence.entity;
// entidade que representa sensores cadastrados no sistema

import com.agro.sensores.domain.enums.TipoSensor;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sensores")
@Getter
@Setter
@NoArgsConstructor

@AllArgsConstructor // - esta annotation gera um construtor com todos os campos/fields
//da classe como argumentos
@EqualsAndHashCode(of = "id")
public class SensorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(nullable = false)
	private String nome;
	
	// localização fisica
	private String localizacao;
	
	// Tipo do sensor
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoSensor tipo;
	
	// Indicar se o sensor está ativo
	@Column(nullable = false)
	private boolean ativo;
}
