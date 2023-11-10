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

@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Long id;
	private String email;
	private String password;
	private String role;
	private List<String> privileges;
	public User(String email, String password) {
		this.email = email;
		this.password = password;
		this.role = "User";
		this.privileges = new ArrayList<String>();
	}
	protected User() {
		
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {

		return this.email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}
	public List<String> getPrivileges(){
		return this.privileges;
	}
	public boolean hasPrivilege(String privilege) {
		return privileges.contains(privilege);
	}
	public boolean addPrivilege(String privilege){
		if(hasPrivilege(privilege)) {
			return false;
		}
		privileges.add(privilege);
		return true;
	}
	public void assignRole(String role, List<String> privileges) {
		this.role = role;
		this.privileges = privileges;
	}
}