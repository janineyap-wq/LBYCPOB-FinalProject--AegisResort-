package com.aegisresort.model;


public record UserAccount(
        String username,
        String password,
        String fullName,
        String role
) {
    public UserAccount {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        if (role == null || role.isBlank()) {
            role = "STAFF";
        }
    }


    public String getUsername() { return username(); }
    public String getPassword() { return password(); }
    public String getFullName() { return fullName(); }
    public String getRole() { return role(); }
}