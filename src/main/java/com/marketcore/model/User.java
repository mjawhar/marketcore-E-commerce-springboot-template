package com.marketcore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name="users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String fullName;
    private String personName;

    @NotBlank(message = "Le numéro de téléphone est obligatoire")
     @Column(nullable = false)
    private String phoneNumber;

    private boolean sellerPro;

    private String resetPasswordToken;
    private LocalDateTime resetPasswordTokenExpiry;

    .....
}
