package com.mustafa.ecommercespringboot.repository;

import com.mustafa.ecommercespringboot.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByUuid(String uuid);
}
