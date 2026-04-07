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
import com.agro.sensores.domain.model.Alerta;
import com.agro.sensores.domain.model.Leitura;
import com.agro.sensores.domain.model.Sensor;
import com.agro.sensores.domain.repository.SensorRepository;
import com.agro.sensores.domain.repository.LeituraRepository;
import com.agro.sensores.domain.service.AlertaService;
import com.agro.sensores.infra.api.dto.AlertaRequest;
import com.agro.sensores.infra.api.dto.AlertaResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/alertas")
@RequiredArgsConstructor
public class AlertaController {

	private final AlertaService alertaService;
	private final SensorRepository sensorRepository;
	private final LeituraRepository leituraRepository;

	@PostMapping
	public ResponseEntity<AlertaResponse> criar(@RequestBody @Valid AlertaRequest request) {
		Sensor sensor = sensorRepository.buscarPorId(request.getSensorId())
				.orElseThrow(() -> new RecursoNaoEncontradoException("Sensor não encontrado"));

        Leitura leitura = leituraRepository.buscarPorId(request.getLeituraId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Leitura não encontrada"));

		Alerta alerta = new Alerta(
				null,
				sensor,
				leitura,
				request.getTitulo(),
				request.getMensagem(),
				request.getSeveridade(),
				request.getAtivo());

		Alerta salvo = alertaService.criar(alerta);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(salvo.getId()).toUri();

		return ResponseEntity.created(uri).body(AlertaResponse.fromDomain(salvo));
	}

	@GetMapping
	public List<AlertaResponse> listar() {
		return alertaService.listarTodos()
				.stream()
				.map(AlertaResponse::fromDomain)
				.toList();
	}

	@GetMapping("/{id}")
	public AlertaResponse buscar(@PathVariable String id) {
		return AlertaResponse.fromDomain(alertaService.buscarPorId(id));
	}

	@PutMapping("/{id}")
	public AlertaResponse atualizar(@PathVariable String id, @RequestBody @Valid AlertaRequest request) {
		Sensor sensor = sensorRepository.buscarPorId(request.getSensorId())
				.orElseThrow(() -> new RecursoNaoEncontradoException("Sensor não encontrado"));

		Leitura leitura = leituraRepository.buscarPorId(request.getLeituraId())
					.orElseThrow(() -> new RecursoNaoEncontradoException("Leitura não encontrada"));

		Alerta alerta = new Alerta(
				id,
				sensor,
				leitura,
				request.getTitulo(),
				request.getMensagem(),
				request.getSeveridade(),
				request.getAtivo());

		return AlertaResponse.fromDomain(alertaService.atualizar(id, alerta));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable String id) {
		alertaService.deletar(id);
	}
}
