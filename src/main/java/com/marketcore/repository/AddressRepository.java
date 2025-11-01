package com.marketcore.repository;

import com.marketcore.model.Address;
import com.marketcore.model.User;
import com.marketcore.model.enums.AddressType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUserOrderByTypeAsc(User user);
    Optional<Address> findByUserAndType(User user, AddressType type);
    List<Address> findByUser(User user);
    boolean existsByUserAndType(User user, AddressType type);
}
