package com.spencer.ItemApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spencer.ItemApp.models.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
