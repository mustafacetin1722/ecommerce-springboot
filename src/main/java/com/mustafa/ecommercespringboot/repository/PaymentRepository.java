package com.mustafa.ecommercespringboot.repository;

import com.mustafa.ecommercespringboot.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Payment findByUuid(String uuid);
    Payment findByUserUuid(String userUuid);
    Payment findByProductUuid(String productUuid);
}
