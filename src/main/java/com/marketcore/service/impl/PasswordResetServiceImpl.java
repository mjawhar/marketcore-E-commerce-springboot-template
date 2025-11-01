package com.marketcore.service.impl;

import com.marketcore.model.User;
import com.marketcore.repository.UserRepository;
import com.marketcore.service.EmailService;
import com.marketcore.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PasswordResetServiceImpl implements PasswordResetService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    private static final int TOKEN_EXPIRY_HOURS = 1;

    @Override
    @Transactional
    public void createPasswordResetToken(String email) {
        // explore this in complete version
    }

    @Override
    public boolean validatePasswordResetToken(String token) {
        // explore this in complete version
        return false;
    }

    @Override
    @Transactional
    public void resetPassword(String token, String newPassword) {
        // explore this in complete version
    }
}
