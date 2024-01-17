package com.example.project.service;

import com.example.project.dto.ProductResponse;
import com.example.project.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Product finById(Long id);

    ProductResponse save(Product product);

    ProductResponse getById(Long id);

    ProductResponse update(Long id, Product product);

    ProductResponse delete(Long id);

    List<Product> saveAll(List<Product> products);


}
