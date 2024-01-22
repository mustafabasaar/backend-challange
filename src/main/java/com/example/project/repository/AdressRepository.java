

package com.example.project.repository;

import com.example.project.entity.Adress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdressRepository extends JpaRepository<Adress,Long> {
}