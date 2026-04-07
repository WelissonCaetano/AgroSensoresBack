package com.agro.sensores.infra.security;

import org.springframework.stereotype.Service;

import com.agro.sensores.infra.persistence.entity.UsuarioEntity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class TokenService {
    @Value("${app.security.secret}")
    private String secret;

    public String gerarToken(UsuarioEntity usuario) {
        // Implementação simples de geração de token (exemplo)
        // Em um cenário real, use uma biblioteca como JWT para criar tokens seguros
        Algorithm algoritimo = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer("agro-sensores")
                .withSubject(usuario.getLogin())
                .withClaim("role", usuario.getRole().name())
                .withExpiresAt(dataExpiracao()) 
                .sign(algoritimo);
    }

//    public String getSubject(String token) {
//        return JWT.require(Algorithm.HMAC256(secret))
//                .withIssuer("agro-sensores")
//                .build()
//                .verify(token)
//                .getSubject();
//    }

    // Validar e extrair a data de expiração do token
    public String getToken(String token) {
        Algorithm algoritimo = Algorithm.HMAC256(secret);

        return JWT.require(algoritimo)
                .withIssuer("agro-sensores")
                .build()
                .verify(token)
                .getSubject();
    }

    private Instant dataExpiracao() {
        // Define a expiração do token (exemplo: 1 hora)
        return LocalDateTime.now()
        .plusHours(2).toInstant(ZoneOffset.of("-3:00"));
    }
}
