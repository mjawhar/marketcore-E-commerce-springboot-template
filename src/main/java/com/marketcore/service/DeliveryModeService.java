package com.marketcore.service;

import com.marketcore.model.DeliveryMode;

import java.util.List;

public interface DeliveryModeService {
    
    List<DeliveryMode> findAllActive();
    
    DeliveryMode findByCode(String code);
    
    DeliveryMode save(DeliveryMode deliveryMode);
    
    void delete(Long id);
}