package com.agro.sensores.infra.persistence.entity;

import com.agro.sensores.domain.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


// as classes entity - dentro do pacote entity - são 
// "entidades" representativas das tables do db que estamos usando
// estas "entidades" irão representar as tables do db para a nossa aplicação

// "entidade" representativa JPA da table de usuarios no DB
@Entity
@Table(name = "usuarios")
@Getter
@Setter


// @NoArgsConstructor //- esta annotation gera automaticamente um construtor-padrão para 
// a classe de referencia public Usuario(){}

 @AllArgsConstructor // - esta annotation gera um construtor com todos os campos/fields
// da classe como argumentos

//@Data // "engloba" tudo: a controversias - na verdade @Data ele é um "pacote" 
//que tem proposito em participar de outras funcionalidades.

@EqualsAndHashCode(of = "id") // aqui, a annotation indica que o hash code precisar ser igual
// ao indicado no id do usuario

public class UsuarioEntity implements UserDetails {

	//id unico gerado automaticamente
	@Id // annotation que indica a chave-primaria da table
	@GeneratedValue(strategy = GenerationType.UUID) // aqui, estamos indicando que os Ids 
	// dos registros da table serão salvos e incrementados de forma automatizada
	private String id;	
	
	// login do usuario
	@Column(nullable=false, unique = true)
	private String login;
	
	// senha criptografada
	@Column(nullable=false)
	private String senha;
	
	// roles de usuario
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private UserRole role;
	
	public UsuarioEntity() {}

	// =============================== Implementação do getAuthoritires =================================
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		// Se o usuário for um ADMIN, ele "ganha" todas as permissões
		// Se for USER comum, "ganha" somente as permissões comuns
		if (this.role == UserRole.ADMIN) {
			return List.of(
					new SimpleGrantedAuthority("ROLE_ADMIN"),
					new SimpleGrantedAuthority("ROLE_USER")
			);
		} else {
			return List.of(new SimpleGrantedAuthority("ROLE_USER"));
		}
	}

	@Override
	public @Nullable String getPassword() {
		return "";
	}

	@Override
	public String getUsername() {
		return "";
	}

	@Override
	public boolean isAccountNonExpired() {
		return UserDetails.super.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return UserDetails.super.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return UserDetails.super.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return UserDetails.super.isEnabled();
	}
}
