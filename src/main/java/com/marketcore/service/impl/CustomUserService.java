package com.marketcore.service.impl;

import com.marketcore.dto.RegisterRequest;
import com.marketcore.model.Role;
import com.marketcore.model.User;
import com.marketcore.repository.RoleRepository;
import com.marketcore.repository.UserRepository;
import com.marketcore.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Set;

@Service
@Primary
@RequiredArgsConstructor
public class CustomUserService implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AutoLoginService autoLoginService;

    @Value("${app.user.default-role:ROLE_SELLER}")
    private String defaultRole;


    @Override
    public User registerSeller(RegisterRequest dto) {
        return register(dto, "ROLE_SELLER");
    }

    @Override
    public User registerBuyer(RegisterRequest dto) {
        // Tous les utilisateurs deviennent maintenant des vendeurs automatiquement
        return register(dto, defaultRole);
    }

    @Override
    public User register(RegisterRequest dto) {
        // Méthode unifiée - tous les utilisateurs deviennent des vendeurs
        return register(dto, defaultRole);
    }

    private User register(RegisterRequest dto, String roleName){
        Role role = roleRepository.findByName(roleName)
                .orElseGet(() -> roleRepository.save(Role.builder().name(roleName).build()));

        User user = User.builder()
                .email(dto.getEmail())
                .fullName(dto.getFullName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .phoneNumber(dto.getPhoneNumber())
                .marquee(dto.getMarquee())
                .roles(Set.of(role))
                .build();

        return userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || auth.getName().equals("anonymousUser")) return null;
        return userRepository.findByEmail(auth.getName()).orElse(null);
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void autoLogin(User user, String rawPassword) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        HttpServletResponse response = attr.getResponse();

        // Utilise le service d'auto-connexion avec le mot de passe en clair
        autoLoginService.autoLogin(request, response, user.getEmail(), rawPassword);
    }

    @Override
    public User updateUserProfile(User user, String fullName, String phoneNumber, String marquee) {
        user.setFullName(fullName);
        user.setPhoneNumber(phoneNumber);
        user.setMarquee(marquee);
        return userRepository.save(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
