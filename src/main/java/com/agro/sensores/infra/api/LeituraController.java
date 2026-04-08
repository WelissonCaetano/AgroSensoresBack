package com.agro.sensores.infra.api;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.agro.sensores.domain.model.Leitura;
import com.agro.sensores.domain.model.Sensor;
import com.agro.sensores.domain.service.LeituraService;
import com.agro.sensores.infra.api.dto.LeituraRequest;
import com.agro.sensores.infra.api.dto.LeituraResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/leituras")
@RequiredArgsConstructor
public class LeituraController {

	private final LeituraService leituraService;

	@PostMapping
	public ResponseEntity<LeituraResponse> criar(@RequestBody @Valid LeituraRequest request) {
		// Cria objeto Leitura temporário com sensor stub (o service resolve o sensor real)
		Leitura leitura = new Leitura(
				null,
				new Sensor(request.getSensorId(), "temp", "temp",
						com.agro.sensores.domain.enums.TipoSensor.SOLO, true),
				request.getValor(),
				request.getDataHora(),
				request.getSensorId()
			);

		Leitura salva = leituraService.criar(request.getSensorId(), leitura);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(salva.getId()).toUri();

		return ResponseEntity.created(uri).body(LeituraResponse.fromDomain(salva));
	}

	@GetMapping
	public List<LeituraResponse> listar(@RequestParam(required = false) String sensorId) {
		List<Leitura> leituras;
		if (sensorId != null && !sensorId.isBlank()) {
			leituras = leituraService.buscarPorSensor(sensorId);
		} else {
			leituras = leituraService.listarTodos();
		}
		return leituras.stream()
				.map(LeituraResponse::fromDomain)
				.toList();
	}

	@GetMapping("/{id}")
	public LeituraResponse buscar(@PathVariable Long id) {
		return LeituraResponse.fromDomain(leituraService.buscarPorId(id));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		leituraService.deletar(id);
	}
}
