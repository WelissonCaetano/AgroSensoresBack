package com.agro.sensores.infra.api;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.agro.sensores.domain.model.Sensor;
import com.agro.sensores.domain.service.SensorService;
import com.agro.sensores.infra.api.dto.AtualizarLocalizacaoDTO;
import com.agro.sensores.infra.api.dto.SensorComLeiturasDTO;
import com.agro.sensores.infra.api.dto.SensorRequest;
import com.agro.sensores.infra.api.dto.SensorResponse;
import com.agro.sensores.application.usecase.AtualizarLocalizacaoSensorUseCase;
import com.agro.sensores.application.usecase.ListarSensoresComLeiturasUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sensores")
@RequiredArgsConstructor
public class SensorController {
	
    private final ListarSensoresComLeiturasUseCase listarComLeiturasUseCase;
	 private final AtualizarLocalizacaoSensorUseCase atualizarLocalizacaoUseCase;

	private final SensorService sensorService;

	@PostMapping
	public ResponseEntity<SensorResponse> criar(@RequestBody @Valid SensorRequest request) {
		Sensor sensor = new Sensor(
				null,
				request.getNome(),
				request.getLocalizacao(),
				request.getTipo(),
				request.getAtivo());

		Sensor salvo = sensorService.criar(sensor);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(salvo.getId()).toUri();

		return ResponseEntity.created(uri).body(SensorResponse.fromDomain(salvo));
	}

	@GetMapping
	public List<SensorResponse> listar() {
		return sensorService.listarTodos()
				.stream()
				.map(SensorResponse::fromDomain)
				.toList();
	}

	@GetMapping("/{id}")
	public SensorResponse buscar(@PathVariable String id) {
		return SensorResponse.fromDomain(sensorService.buscarPorId(id));
	}

	@PutMapping("/{id}")
	public SensorResponse atualizar(@PathVariable String id, @RequestBody @Valid SensorRequest request) {
		Sensor sensor = new Sensor(
				id,
				request.getNome(),
				request.getLocalizacao(),
				request.getTipo(),
				request.getAtivo());

		return SensorResponse.fromDomain(sensorService.atualizar(id, sensor));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable String id) {
		sensorService.deletar(id);
	}

	// 4️. LISTAR SENSORES COM LEITURAS
	@GetMapping("/com-leituras")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<SensorComLeiturasDTO>> listarComLeituras() {
		return ResponseEntity.ok(listarComLeiturasUseCase.executar());
	}

	 // 7️. ATUALIZAR LOCALIZAÇÃO (ADMIN)
    @PutMapping("/{id}/localizacao")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> atualizarLocalizacao(
            @PathVariable String id,
            @RequestBody @Valid AtualizarLocalizacaoDTO dto) {

        atualizarLocalizacaoUseCase.executar(id, dto.localizacao());
        return ResponseEntity.ok().build();
    }
}
