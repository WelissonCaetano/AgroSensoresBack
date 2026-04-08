package com.agro.sensores.infra.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.agro.sensores.infra.persistence.repository.JpaUsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter{
    private final TokenService tokenService;
    private final JpaUsuarioRepository usuarioRepository;

    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,   
        FilterChain filterChain
    ) throws IOException, ServletException{
        String token = recuperarToken(request);

        if(token != null) {
            String login = tokenService.getToken(token);
            var usuario = usuarioRepository.findByLogin(login);
            if(usuario.isPresent()) {
                var autetication = new UsernamePasswordAuthenticationToken(
                    usuario.get(),
                    null,
                    usuario.get().getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(autetication);
            }
        }

        filterChain.doFilter(request, response);
    }
    private String recuperarToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(header == null) {
            return null;
        }
        return header.replace("Bearer ", "");
    }
}
