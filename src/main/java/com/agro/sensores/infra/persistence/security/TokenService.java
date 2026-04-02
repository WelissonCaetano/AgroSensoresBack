package com.agro.sensores.infra.persistence.security;

// Este será o nosso "guardião" da aplicação. Irá operar o contexe de seguraça baseado em tokens
// Vamos, para este propósito, implementar o conceito de JWT: funciona como um "crachá"

import com.agro.sensores.infra.persistence.entity.UsuarioEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    // Chave secreta dp JWT(posteriormente vamos definir sua origem no arquivo appication,properties)
    // Composto com as cred^nciais necessárias para autenticação/autorizaçãode acesso ao usuário
    @Value("${api.security.secret}")
    private String secret;

    // Agora vamos criar o processo que gera os tokens
    public String getToken(UsuarioEntity usuario) {

        // Definição do algotirimo da assinatura do token
        Algorithm algoritimo = Algorithm.HMAC256(secret);

        // Criar token com as informações do usuário.
        return JWT.create()
                .withIssuer("agro-sensores") // Emissor
                .withSubject(usuario.getLogin()) // Usuário/assunto
                .withClaim("role", usuario.getRole().name()) // Role do usuário embarcado
                .withExpiresAt(getTokenExpiration()) // Data de expiração do token
                .sign(algoritimo);
    }

    // Validar e extrair a data de expiração do token
    public String getToken(String token) {
        Algorithm algoritimo = Algorithm.HMAC256(secret);

        return JWT.require(algoritimo)
                .withIssuer("agro-sensores")
                .build()
                .verify(token)
                .getSubject();
    }

    // Definição de validade de expiração do token
    public Instant getTokenExpiration() {
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }

}

