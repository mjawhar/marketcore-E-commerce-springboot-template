package com.marketcore.repository;

import com.marketcore.model.DeliveryMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryModeRepository extends JpaRepository<DeliveryMode, Long> {
    
    Optional<DeliveryMode> findByCode(String code);
    
    List<DeliveryMode> findByActiveTrue();
}