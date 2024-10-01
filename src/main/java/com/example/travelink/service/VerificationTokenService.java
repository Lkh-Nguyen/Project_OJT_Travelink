package com.example.travelink.service;

import java.util.Calendar;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travelink.model.Account;
import com.example.travelink.model.VerificationToken;
import com.example.travelink.repository.VerificationTokenRepository;

@Service
public class VerificationTokenService {
    @Autowired
    private VerificationTokenRepository tokenRepository;

    public VerificationToken createVerificationToken(Account account) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setAccount(account);

        // Set expiry date, e.g., 24 hours later
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 24);
        verificationToken.setExpiryDate(cal.getTime());

        return tokenRepository.save(verificationToken);
    }

    public VerificationToken findByToken(String token) {
        return tokenRepository.findByToken(token);
    }
}
