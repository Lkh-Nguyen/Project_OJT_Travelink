package com.example.travelink.service;

import com.example.travelink.model.Account;
import com.example.travelink.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

   @Autowired
    private CustomerRepository account_Repository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Use PasswordEncoder to securely hash the passwords

    // Change Password Method
    public boolean changePassword(int accountId, String currentPassword, String newPassword) {
        Account account = account_Repository.findById(accountId).orElse(null);
        if (account == null) {
            return false; // Account not found
        }

        // Check if the current password matches the one stored in the database
        if (!passwordEncoder.matches(currentPassword, account.getPassword())) {
            return false; // Current password is incorrect
        }

        // Encode the new password and save it
        account.setPassword(passwordEncoder.encode(newPassword));
        account_Repository.save(account);
        return true; // Password change successful
    }

    public Account getAccountById(int accountId) {
        return account_Repository.findById(accountId).orElse(null);
    }

}
