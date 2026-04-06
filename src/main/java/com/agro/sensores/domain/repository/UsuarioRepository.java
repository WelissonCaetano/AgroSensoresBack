package com.agro.sensores.domain.repository;

import java.util.Optional;

import com.agro.sensores.domain.model.Usuario;

public interface UsuarioRepository {
	Optional<Usuario> buscarPorId(String id);
	Optional<Usuario> buscarPorLogin(String longn);
	
	void salvar(Usuario usuario);
	void deletar(String id);
	boolean existeLogin(String login);
}
