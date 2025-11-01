package com.marketcore.repository;

import com.marketcore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findByRoles_Name(String roleName);

    List<User> findByEmailContainingIgnoreCase(String q);

    boolean existsByEmail(String email);

    // Nouvelle méthode pour récupérer uniquement les vendeurs professionnels
    List<User> findByRoles_NameAndSellerProTrue(String roleName);

    // Méthode pour trouver un utilisateur par token de réinitialisation
    Optional<User> findByResetPasswordToken(String token);
}
