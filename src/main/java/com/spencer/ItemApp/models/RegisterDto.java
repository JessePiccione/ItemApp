package com.spencer.ItemApp.models;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class RegisterDto {
    public String username;
    public String password;
    public String passwordChecker;
    public String role;
}