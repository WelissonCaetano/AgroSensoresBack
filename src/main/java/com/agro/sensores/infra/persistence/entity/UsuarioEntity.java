package com.agro.sensores.infra.persistence.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.agro.sensores.domain.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
//@NoArgsConsrtructo
@AllArgsConstructor
//@Data
@EqualsAndHashCode(of = "id")
public class UsuarioEntity implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Column(nullable = false, unique = true)
	private String login;
	
	@Column(nullable = false)
	private String senha;
		
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserRole role;

	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.role == UserRole.ADMIN) {
			return List.of(
					new SimpleGrantedAuthority("ROLE_ADMIN"),
					new SimpleGrantedAuthority("ROLE_USER")
			);
		}else {
			return List.of(new SimpleGrantedAuthority("ROLE_USER"));
		}
		
	}
	
	public UsuarioEntity() {}
	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return login;
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
