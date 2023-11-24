package com.spencer.ItemApp.models;

import jakarta.persistence.JoinColumn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity(name = "users")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String password;
	private String role;
	private String privileges;
	public User(String email, String password, String role) {
		this.email = email;
		this.password = password;
		this.role = role;
		this.privileges = "";
	}
	protected User() {
		
	}
	public boolean isAdmin() {
		return role.equals("ADMIN");
	}

	public void setRole(String role) {
		this.role = role;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean hasPrivilege(String privilege) {
		return privileges.contains(privilege);
	}
	public boolean addPrivilege(String privilege){
		if(hasPrivilege(privilege)) {
			return false;
		}
		this.privileges+=privilege;
		return true;
	}
	public void assignRole(String role, String privileges) {
		this.role = role;
		this.privileges = privileges;
	}

	public void setId(Long id) {
		this.id = id;
	}
}