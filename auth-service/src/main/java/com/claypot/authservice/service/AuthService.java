package com.claypot.authservice.service;

import com.claypot.authservice.dto.SignupRequest;
import com.claypot.authservice.entity.AppUser;
import com.claypot.authservice.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupRequest signupRequest) {

        if(appUserRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in exist!!");
        }

        AppUser appUser = AppUser.builder()
                .username(signupRequest.getUsername())
                .email(signupRequest.getEmail())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .build();

        appUserRepository.save(appUser);
    }
}
