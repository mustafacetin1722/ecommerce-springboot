package com.mustafa.ecommercespringboot.repository;

import com.mustafa.ecommercespringboot.model.Review;
import com.mustafa.ecommercespringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    Optional<Review> findByUuid(String uuid);
    List<Review> findAllByUser(User user); // uuid
    List<Review> findAllByProductUuid(String productUuid);
    Review findByUserUuidAndProductUuid(String userUuid, String productUuid);
    String deleteByUuid(String uuid);
    List<Review> findAllByUuid(String uuid);
    Review findByUserUuid(String userUuid);
}
