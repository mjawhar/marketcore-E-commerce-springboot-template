package com.marketcore.config;

import com.marketcore.service.impl.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    // API REST
    @Bean
    @Order(1)
    public SecurityFilterChain apiChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/api/**")
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/products/search",
                    ...
    }

    // ADMIN
    @Bean
    @Order(2)
    public SecurityFilterChain adminChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/admin/**")
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/admin/login").permitAll()
                    .anyRequest().hasRole("ADMIN"))
            .formLogin(f -> f.loginPage("/admin/login").permitAll()
                             .defaultSuccessUrl("/admin", true))
                ...
    }

    // SELLER
    @Bean
    @Order(3)
    public SecurityFilterChain sellerChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/seller/**")
                ...
    }

    // PUBLIC (CLIENT)
    @Bean
    @Order(4)
    public SecurityFilterChain clientChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/product/**", "/category/**", "/produit/**", "/categorie/**",
                                 "/cart/**", "/images/**",
                                 "/login", "/register/**",
                                 "/forgot-password", "/reset-password",
                                 "/css/**","/js/**","/img/**","/webjars/**","/error/**","/favicon.ico")
                .....
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider p = new DaoAuthenticationProvider();
        p.setUserDetailsService(userDetailsService);
        p.setPasswordEncoder(passwordEncoder());
        return p;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
