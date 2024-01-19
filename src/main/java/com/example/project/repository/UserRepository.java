package com.example.project.repository;

import com.example.project.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser,Long> {
    @Query(value = "SELECT * FROM finalproject.user as u WHERE u.email= :email",nativeQuery = true)
    Optional<AppUser> findUserByEmail(String email);
}
