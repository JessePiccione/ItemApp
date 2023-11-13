package com.spencer.ItemApp.models;

import lombok.Data;

@Data
public class RegisterDto {
	public String username;
	public String password;
	public String role;
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
	public String getRole() {
		return this.role;
	}
}