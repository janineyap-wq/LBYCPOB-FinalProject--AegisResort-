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
        if (role == null || role.isBlank()) {
            role = "STAFF"; // Default fallback role
        }
    }

    // --- OPTIONAL GETTER ALIASES (For compatibility with legacy bean callers) ---
    public String getUsername() { return username(); }
    public String getPassword() { return password(); }
    public String getFullName() { return fullName(); }
    public String getRole() { return role(); }
}