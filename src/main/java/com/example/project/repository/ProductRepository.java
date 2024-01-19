package com.example.project.repository;

import com.example.project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "SELECT * FROM finalproject.clothes INNER JOIN finalproject.cathegory ON finalproject.cathegory.id = finalproject.clothes.cathegory_id WHERE finalproject.cathegory.gender = :gender",nativeQuery = true)
    List<Product> findByCategoryGender(String gender);

    @Query(value = "SELECT * FROM finalproject.clothes INNER JOIN finalproject.cathegory ON finalproject.cathegory.id = finalproject.clothes.cathegory_id WHERE finalproject.cathegory.cathegory_name = :cathegory_name",nativeQuery = true)
    List<Product> findByCategoryName(String cathegory_name);
    @Query(value = "SELECT * FROM finalproject.clothes INNER JOIN finalproject.cathegory ON finalproject.cathegory.id = finalproject.clothes.cathegory_id WHERE finalproject.cathegory.cathegory_name = :cathegory_name AND finalproject.cathegory.gender = :gender ",nativeQuery = true)
    List<Product> findByCategoryNameAndGender(String cathegory_name,String gender);
}
