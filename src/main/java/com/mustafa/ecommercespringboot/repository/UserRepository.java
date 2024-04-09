package com.mustafa.ecommercespringboot.repository;

import com.mustafa.ecommercespringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    void deleteByUuid(String uuid);
    Optional<User> findByUuid(String uuid);
}
