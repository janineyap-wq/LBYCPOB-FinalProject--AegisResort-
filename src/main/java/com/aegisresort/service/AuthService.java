package com.aegisresort.service;

import com.aegisresort.model.UserAccount;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    private final Map<String, UserAccount> userDatabase = new HashMap<>();

    public AuthService() {
        seedDefaultAccounts();
    }

    private void seedDefaultAccounts() {
        // Seed initial authorized staff credentials
        UserAccount admin = new UserAccount("admin", "admin123", "Chief Security Officer", "MANAGER");
        UserAccount staff = new UserAccount("staff", "staff123", "Janine Officer", "SECURITY_OFFICER");

        userDatabase.put(admin.username().toLowerCase(), admin);
        userDatabase.put(staff.username().toLowerCase(), staff);
    }


    public Optional<UserAccount> authenticate(String username, String password) {
        if (username == null || password == null) {
            return Optional.empty();
        }

        UserAccount user = userDatabase.get(username.trim().toLowerCase());
        if (user != null && user.password().equals(password)) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public Optional<UserAccount> findByUsername(String username) {
        if (username == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(userDatabase.get(username.trim().toLowerCase()));
    }
}