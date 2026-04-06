package com.agro.sensores.domain.model;

import com.agro.sensores.domain.enums.UserRole;
import com.agro.sensores.domain.exception.ValidacaoException;

import lombok.Getter;
import lombok.ToString;


@Getter
@ToString(exclude = "senha")
public class Usuario{
	// Identificador unico do usuario
		private String id;
		
		// Login do usuario
		private String login;
		
		// senha do usuario
		private String senha;
		
		//definir a role do usuario
		private UserRole role;
	
		public Usuario(
				String id,
				String login,
				String senha,
				UserRole role
				) {
			if(login == null || login.isBlank()) {
				// vamos lnaçar nossa exception custom
				throw new ValidacaoException("Login é obrigatorio!");
			}
			
			if(senha == null || senha.length() < 6) {
				throw new ValidacaoException("Sua senha deve ter pelo meno 6 caracteres");
			}
			
			this.id = id;
			this.login = login;
			this.senha = senha;
			this.role = role;
		}
		
		public boolean isAdmin() {
			return this.role == UserRole.ADMIN;
		}

}
