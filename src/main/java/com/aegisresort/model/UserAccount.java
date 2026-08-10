package com.aegisresort.model;

/**
 * Domain record holding authorized staff account information.
 */
public record UserAccount(
        String username,
        String password,
        String fullName,
        String role // e.g., "MANAGER", "SECURITY_OFFICER"
) {
    public UserAccount {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
    }
}
