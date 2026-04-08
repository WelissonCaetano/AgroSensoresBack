package com.agro.sensores.infra.api.dto;



import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponseDTO {

	@NotBlank
	private String login;

	@NotBlank
	private String role;


}
