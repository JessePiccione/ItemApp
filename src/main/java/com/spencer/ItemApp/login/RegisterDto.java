package com.spencer.ItemApp.login;

import lombok.Data;

@Data
public class RegisterDto {
	public String username;
	public String password;
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
	
}