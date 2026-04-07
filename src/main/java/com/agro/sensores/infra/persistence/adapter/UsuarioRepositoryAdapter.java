package com.agro.sensores.infra.persistence.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.agro.sensores.domain.model.Usuario;
import com.agro.sensores.domain.repository.UsuarioRepository;
import com.agro.sensores.infra.persistence.entity.UsuarioEntity;
import com.agro.sensores.infra.persistence.repository.JpaUsuarioRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioRepositoryAdapter implements  UsuarioRepository {
	
	private final JpaUsuarioRepository jpa;
	public Optional<Usuario> buscarPorId(String id){
		return jpa.findById(id)
				.map(this::toDomain);
	}
	
	public Optional <Usuario> buscarPorLogin(String login){
		return jpa.findByLogin(login)
				.map(this::toDomain);
	}
	
	public void salvar (Usuario usuario) {
		jpa.save(toEntity(usuario));
	}
	
	public void deletar(String id) {
		jpa.deleteById(id);
	}
	
	public boolean existeLogin(String login) {
		return jpa.findByLogin(login).isPresent();
	}
	
	public Usuario toDomain(UsuarioEntity entity) {
		return new Usuario(
				entity.getId(),
				entity.getLogin(),
				entity.getSenha(),
				entity.getRole()
				
				);
	}
	
	private UsuarioEntity toEntity (Usuario usuario) {
		return new UsuarioEntity(
				usuario.getId(),
				usuario.getLogin(),
				usuario.getSenha(),
				usuario.getRole()
				);
	}
	
}
