package com.agro.sensores.infra.api.dto;


import jakarta.validation.constraints.NotBlank;

public record AtualizarLocalizacaoDTO(
	    @NotBlank
	    String localizacao
	) {}
