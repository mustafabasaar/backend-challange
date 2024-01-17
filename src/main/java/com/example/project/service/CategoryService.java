package com.example.project.service;

import com.example.project.dto.CategoryResponse;
import com.example.project.entity.Category;

import java.util.List;

public interface CategoryService {
    Category findById(Long id);
    CategoryResponse save(Category category);
    CategoryResponse getById(Long  id);
    CategoryResponse update(Long id,Category category);
    CategoryResponse delete(Long id);
    List<CategoryResponse> getAll();
}
