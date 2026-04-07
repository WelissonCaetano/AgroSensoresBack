package com.agro.sensores.infra.api.dto;

import com.agro.sensores.domain.model.Area;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AreaResponse {

	private String id;
	private String nome;
	private String descricao;
	private String formato;
	private Integer tamanho;
	private Float longitude;
	private Float latitude;
	private String localizacao;
	private Boolean ativo;

	public static AreaResponse fromDomain(Area area) {
		return new AreaResponse(
				area.getId(),
				area.getNome(),
				area.getDescricao(),
				area.getFormato(),
				area.getTamanho(),
				area.getLongitude(),
				area.getLatitude(),
				area.getLocalizacao(),
				area.isAtivo());
	}
}
