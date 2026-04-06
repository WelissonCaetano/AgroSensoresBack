package com.agro.sensores.infra.persistence.security;

// Irá interceptar qualquer requisição que for feita para a api, antes de chegar em qualquer endpoint
// porque precisamos saber quem está tentande acessar a aplicação

import com.agro.sensores.infra.persistence.repository.JpaUsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final JpaUsuarioRepository jpaUsuarioRepository;

    // Ao praticar o mecanismo de herança, abaixo, estamos tentando garantir que esse processo
    // de validação aconteça, exatamente, uma vez por requisição - para evitar processamentos redundantes
    @Override
    protected void doFilterInternal(
            HttpServletRequest request, // Pedido da aplicação cliente
            HttpServletResponse response, // Resposta do servidor
            FilterChain filterChain // Sequência de filtragem
    ) throws ServletException, IOException {

        // Definição da DIs
        // Aquiiremos recuperar o token do header
        String token = recuperarToken(request);

        String login = null;
        if (token != null) {
            login = tokenService.getToken(token);
        }

        // Buscando o usuário no DB
        var usuario = jpaUsuarioRepository.findByLogin(login);
        if (usuario.isPresent()) {

            // Criar/definir processo de atutenticação
            var autorizado = new UsernamePasswordAuthenticationToken(usuario.get(), usuario.get());

            // Define o usuario autenticado no contexto
            SecurityContextHolder.getContext().setAuthentication(autorizado);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {

        String header = request.getHeader("Authorization");

        if (header == null) {
            return null;
        }

        return header.replace("Bearer ", "");
    }
}
