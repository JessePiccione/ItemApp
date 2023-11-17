package com.spencer.ItemApp.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SendableUser {
	//class level 
	public static List<SendableUser> createSendableUserList(List<User> users){
		List<SendableUser> sendableUserList = new ArrayList<SendableUser>(); 
		for (User u: users) {
			sendableUserList.add(new SendableUser(u));
		}
		return sendableUserList;
	}
	private String email;
	private String role;
	private List<String> privileges;
	public SendableUser() {
		this.email = "";
		this.role = "USER";
		this.privileges = new ArrayList<String>();
	}
	public SendableUser(User u) {
		this.email = u.getEmail();
		this.role = u.getRole();
		this.privileges = u.getPrivileges();
	}
	public SendableUser(String email, String role, List<String> privileges) {
		this.email = email;
		this.role = role;
		this.privileges = privileges;
	}
	public String getEmail() {
		return this.email;
	}
	public String getRole() {
		return this.role;
	}
	public List<String> getPrivileges(){
		return this.privileges;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setPrivileges(List<String> privileges) {
		this.privileges = privileges; 
	}
	public boolean hasPrivilege(String privilege) {
		return this.privileges.contains(privilege);
	}
	public boolean isAdmin() {
		return this.role.equals("ADMIN");
	}
	public boolean hasUsername(String username) {
		return this.email.equals(username);
	}
	
}
