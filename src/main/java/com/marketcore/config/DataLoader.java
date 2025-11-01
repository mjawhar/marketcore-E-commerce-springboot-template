package com.marketcore.config;

import com.marketcore.model.*;
import com.marketcore.repository.SiteSettingRepository;
import com.marketcore.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner loadData(RoleRepository roleRepo,
                               UserRepository userRepo,
                               CategoryRepository catRepo,
                               ProductRepository productRepo, SiteSettingRepository settingRepo){
        return args -> {
            if(roleRepo.count() == 0){
                roleRepo.saveAll(List.of(
                        Role.builder().name("ROLE_BUYER").build(),
                        Role.builder().name("ROLE_SELLER").build(),
                        Role.builder().name("ROLE_ADMIN").build()
                ));
            }

            ....
            }

            .....
    }
}
