package com.mustafa.ecommercespringboot.repository;

import com.mustafa.ecommercespringboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByUuid(String uuid);
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    List<Product> findByPrice(double price);
}
