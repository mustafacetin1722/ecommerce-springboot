package com.mustafa.ecommercespringboot.repository;

import com.mustafa.ecommercespringboot.model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping,Long> {
    Optional<Shipping> findByUuid(String uuid);
}
