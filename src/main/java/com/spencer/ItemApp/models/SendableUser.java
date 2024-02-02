package com.spencer.ItemApp.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class SendableUser {
    //class level
    public static List<SendableUser> createSendableUserList(List<User> users) {
        List<SendableUser> sendableUserList = new ArrayList<SendableUser>();
        for (User u : users) {
            sendableUserList.add(new SendableUser(u));
        }
        return sendableUserList;
    }
    private long id;
    private String email;
    private String role;
    private String privileges;
    public SendableUser() {
        this.id = 0;
        this.email = "";
        this.role = "User";
        this.privileges = "";
    }
    public SendableUser(User u) {
        this.id = u.getId();
        this.email = u.getEmail();
        this.role = u.getRole();
        this.privileges = u.getPrivileges();
    }
    public SendableUser(Long id, String email, String role, String privileges) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.privileges = privileges;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }
    public boolean hasPrivilege(String privilege) {
        return this.privileges.contains(privilege);
    }
    public boolean isAdmin() {
        return "ADMIN".equals(this.role);
    }
    public boolean hasUsername(String username) {
        return this.email.equals(username);
    }
    public void setId(Long id) {
        this.id = id;
    }
}
