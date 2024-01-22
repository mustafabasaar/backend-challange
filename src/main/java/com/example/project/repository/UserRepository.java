package com.example.project.repository;

import com.example.project.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser,Long> {
    @Query(value = "SELECT u FROM AppUser u WHERE u.email= :email")
    Optional<AppUser> findUserByEmail(String email);
    @Query(value="SELECT u FROM AppUser  u WHERE u.id= :id ")
    Optional<AppUser> findUserById(Long id);
}
