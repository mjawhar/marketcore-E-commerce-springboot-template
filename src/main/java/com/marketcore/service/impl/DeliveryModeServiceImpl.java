package com.marketcore.service.impl;

import com.marketcore.model.DeliveryMode;
import com.marketcore.repository.DeliveryModeRepository;
import com.marketcore.service.DeliveryModeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryModeServiceImpl implements DeliveryModeService {

    private final DeliveryModeRepository deliveryModeRepository;

    @Override
    public List<DeliveryMode> findAllActive() {
        // explore this in complete version
        return null;
    }

    @Override
    public DeliveryMode findByCode(String code) {
        // explore this in complete version
        return null;
    }

    @Override
    public DeliveryMode save(DeliveryMode deliveryMode) {
        // explore this in complete version
        return null;
    }

    @Override
    public void delete(Long id) {
        // explore this in complete version
    }
}