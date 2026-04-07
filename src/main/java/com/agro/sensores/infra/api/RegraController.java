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

import com.agro.sensores.domain.exception.RecursoNaoEncontradoException;
import com.agro.sensores.domain.model.Regra;
import com.agro.sensores.domain.model.Sensor;
import com.agro.sensores.domain.repository.SensorRepository;
import com.agro.sensores.domain.service.RegraService;
import com.agro.sensores.infra.api.dto.RegraRequest;
import com.agro.sensores.infra.api.dto.RegraResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/regras")
@RequiredArgsConstructor
public class RegraController {

	private final RegraService regraService;
	private final SensorRepository sensorRepository;

	@PostMapping
	public ResponseEntity<RegraResponse> criar(@RequestBody @Valid RegraRequest request) {
		Sensor sensor = sensorRepository.buscarPorId(request.getSensorId())
				.orElseThrow(() -> new RecursoNaoEncontradoException("Sensor não encontrado"));

		Regra regra = new Regra(
				null,
				request.getNome(),
				sensor,
				request.getValorMinimo(),
				request.getValorMaximo(),
				request.getAtivo());

		Regra salva = regraService.criar(regra);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(salva.getId()).toUri();

		return ResponseEntity.created(uri).body(RegraResponse.fromDomain(salva));
	}

	@GetMapping
	public List<RegraResponse> listar() {
		return regraService.listarTodos()
				.stream()
				.map(RegraResponse::fromDomain)
				.toList();
	}

	@GetMapping("/{id}")
	public RegraResponse buscar(@PathVariable String id) {
		return RegraResponse.fromDomain(regraService.buscarPorId(id));
	}

	@PutMapping("/{id}")
	public RegraResponse atualizar(@PathVariable String id, @RequestBody @Valid RegraRequest request) {
		Sensor sensor = sensorRepository.buscarPorId(request.getSensorId())
				.orElseThrow(() -> new RecursoNaoEncontradoException("Sensor não encontrado"));

		Regra regra = new Regra(
				id,
				request.getNome(),
				sensor,
				request.getValorMinimo(),
				request.getValorMaximo(),
				request.getAtivo());

		return RegraResponse.fromDomain(regraService.atualizar(id, regra));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable String id) {
		regraService.deletar(id);
	}
}