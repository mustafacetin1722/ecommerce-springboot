package com.mustafa.ecommercespringboot.repository;

import com.mustafa.ecommercespringboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByUuid(String uuid);
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    List<Product> findByPrice(double price);
    @Query("SELECT p FROM Product p JOIN p.promotionList promo WHERE promo.uuid = :promotionUuid")
    List<Product> findByPromotionUuid(String promotionUuid);
    @Query("SELECT p FROM Product p JOIN p.userLists user WHERE user.uuid = :userUuid")
    List<Product> findByUserUuid(String userUuid);
    @Query("SELECT p FROM Product p JOIN p.category c WHERE c.uuid = :categoryUuid")
    List<Product> findByCategoryUuid(String categoryUuid);
}
