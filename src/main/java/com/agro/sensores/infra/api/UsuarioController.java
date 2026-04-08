package com.agro.sensores.infra.api;




import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import com.agro.sensores.infra.api.dto.UsuarioRequestDTO;
import com.agro.sensores.application.usecase.CadastrarUsuarioUseCase;
import com.agro.sensores.infra.api.dto.SensorResponse;
import com.agro.sensores.infra.api.dto.UsuarioRequestDTO;
import com.agro.sensores.infra.api.dto.UsuarioResponseDTO;


// Controller de usuários
@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final CadastrarUsuarioUseCase useCase;

    // Endpoint de cadastro
    @PostMapping
   // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid UsuarioRequestDTO dto) {

    	// Agora o Java reconhece que dto.role() é um UserRole
        // e o método executar do UseCase também espera um UserRole.
        useCase.executar(dto.login(), dto.senha(), dto.role());

        // Retorna HTTP 201 (Created)
        return ResponseEntity.status(201).build();
    }
    // @GetMapping
	// public List<UsuarioResponseDTO> listar() {
	// 	return sensorService.listarTodos()
	// 			.stream()
	// 			.map(SensorResponse::fromDomain)
	// 			.toList();
	// }

}

