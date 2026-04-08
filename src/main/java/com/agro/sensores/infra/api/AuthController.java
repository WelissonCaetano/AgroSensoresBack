package com.agro.sensores.infra.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agro.sensores.infra.api.dto.LoginRequestDTO;
import com.agro.sensores.infra.api.dto.TokenResponseDTO;
import com.agro.sensores.application.usecase.AutenticarUsuarioUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

//Controller responsável por autenticação
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

 private final AutenticarUsuarioUseCase useCase;

 // Endpoint de login
 @PostMapping("/login")
 public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid LoginRequestDTO dto) {

     // Executa autenticação
     String token = useCase.executar(dto.login(), dto.senha());

     // Retorna token
     return ResponseEntity.ok(new TokenResponseDTO(token));
 }
}