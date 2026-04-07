package com.agro.sensores.infra.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AreaRequest {

	@NotBlank
	private String nome;

	private String descricao;

	private String formato;

	private Integer tamanho;

	private Float longitude;

	private Float latitude;

	@NotBlank
	private String localizacao;

	@NotNull
	private Boolean ativo;
}
