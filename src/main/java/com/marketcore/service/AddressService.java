package com.marketcore.service;

import com.marketcore.model.Address;
import com.marketcore.model.User;
import com.marketcore.model.enums.AddressType;
import com.marketcore.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AddressService {

    private final AddressRepository addressRepository;

    public List<Address> getUserAddresses(User user) {
        // explore this in complete version
    }

    public Optional<Address> getUserAddressByType(User user, AddressType type) {
        // explore this in complete version
    }

    public Address saveAddress(Address address) {
        // explore this in complete version
    }

    public Address createOrUpdateAddress(User user, String nom, String adresse, String ville,
                                       String codePostal, String telephone, AddressType type) {
        // explore this in complete version
    }

    public void deleteAddress(Long addressId, User user) {
        // explore this in complete version
    }

    public Optional<Address> findById(Long id) {
        // explore this in complete version
    }

    public boolean userHasAddressOfType(User user, AddressType type) {
        // explore this in complete version
    }
}
