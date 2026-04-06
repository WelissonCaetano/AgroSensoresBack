package com.agro.sensores.infra.api;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.agro.sensores.domain.model.Area;
import com.agro.sensores.domain.service.AreaService;
import com.agro.sensores.infra.api.dto.AreaRequest;
import com.agro.sensores.infra.api.dto.AreaResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/areas")
@RequiredArgsConstructor
public class AreaController {

	private final AreaService areaService;

	@PostMapping
	public ResponseEntity<AreaResponse> criar(@RequestBody @Valid AreaRequest request) {
		Area area = new Area(
				null,
				request.getNome(),
				request.getDescricao(),
				request.getFormato(),
				request.getTamanho(),
				request.getLongitude(),
				request.getLatitude(),
				request.getLocalizacao(),
				request.getAtivo());

		Area salva = areaService.criar(area);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(salva.getId()).toUri();

		return ResponseEntity.created(uri).body(AreaResponse.fromDomain(salva));
	}

	@GetMapping
	public List<AreaResponse> listar() {
		return areaService.listarTodos()
				.stream()
				.map(AreaResponse::fromDomain)
				.toList();
	}

	@GetMapping("/{id}")
	public AreaResponse buscar(@PathVariable String id) {
		return AreaResponse.fromDomain(areaService.buscarPorId(id));
	}

	@PutMapping("/{id}")
	public AreaResponse atualizar(@PathVariable String id, @RequestBody @Valid AreaRequest request) {
		Area area = new Area(
				id,
				request.getNome(),
				request.getDescricao(),
				request.getFormato(),
				request.getTamanho(),
				request.getLongitude(),
				request.getLatitude(),
				request.getLocalizacao(),
				request.getAtivo());

		return AreaResponse.fromDomain(areaService.atualizar(id, area));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable String id) {
		areaService.deletar(id);
	}
}
