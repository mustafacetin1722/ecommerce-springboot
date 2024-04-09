package com.mustafa.ecommercespringboot.repository;

import com.mustafa.ecommercespringboot.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
}
