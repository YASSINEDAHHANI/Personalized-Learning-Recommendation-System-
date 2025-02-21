package com.example.recommendation_system.dto;

import com.example.recommendation_system.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private UserRole userRole;

//    public UserDto(Long id, String username, String email, String password, UserRole userRole) {
//        this.id = id;
//        this.username = username;
//        this.email = email;
//        this.password = password;  // You may set this to null for security
//        this.userRole = userRole;
//    }
//
//    public UserDto() {
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
