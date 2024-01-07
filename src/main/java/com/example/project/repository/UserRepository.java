package com.example.project.repository;

import com.example.project.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser,Long> {
    @Query("SELECT u FROM ApplicationUser u WHERE u.email = :email")
    Optional<AppUser> findUserByEmail(String email);
}