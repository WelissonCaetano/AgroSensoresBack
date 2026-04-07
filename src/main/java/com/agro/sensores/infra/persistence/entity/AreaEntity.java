package com.agro.sensores.infra.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "areas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AreaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column
	private String descricao;
	
	@Column
	private String formato;
	
	@Column
	private Integer tamanho;
	
	@Column
	private Float longitude;
	
	@Column
	private Float latitude;
	
	@Column(nullable = false)
	private String localizacao;
	
	@Column(nullable = false)
	private boolean ativo;
}
