package com.skillstorm.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class AppUser implements UserDetails {

	@Id
	private String username; // email
	private String password;
	
	// Table called Authorities/Roles/whatever
	private String role; // For RBAC, USER
	

	public AppUser() {
		super();
	}

	public AppUser(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// These authorities can be roles OR attributes
		// ROLE_USER or r_file
		// RBAC, it expects a format of ROLE_<role here>
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
		authorities.add(authority);
		return authorities;
	}

	@Override
	public String getUsername() {
		// if using email as the username, return it here
		return username;
	}
	
	

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		// If your account after so many months of not using, it becomes expired
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// Failed the login times in a row
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// Good for when you enforce password rotation
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		// Good for apps where you allow the user to their disable their account
		
		return true;
	}
}
