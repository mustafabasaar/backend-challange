package com.example.project.service;


import com.example.project.converter.DtoConverter;
import com.example.project.dto.CategoryResponse;
import com.example.project.entity.Category;
import com.example.project.exceptions.EcommerceException;
import com.example.project.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category findById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            return optionalCategory.get();
        }
        throw new EcommerceException("The category with given id does not exist! ID: "+id, HttpStatus.NOT_FOUND);
    }

    @Override
    public CategoryResponse save(Category category) {
        return DtoConverter.convertToCategoryResponse(categoryRepository.save(category));
    }


    @Override
    public CategoryResponse getById(Long id) {
        return DtoConverter.convertToCategoryResponse(findById(id));
    }
    @Override
    public CategoryResponse update(Long id, Category category){
        Category willUpdateCategory = findById(id);
        willUpdateCategory.setTitle(category.getTitle());
        willUpdateCategory.setGender(category.getGender());

        return DtoConverter.convertToCategoryResponse(categoryRepository.save(willUpdateCategory));
    }

    @Override
    public CategoryResponse delete(Long id) {
        Category willDeleteCategory = findById(id);
        categoryRepository.delete(willDeleteCategory);
        return DtoConverter.convertToCategoryResponse(willDeleteCategory);
    }
    @Override
    public List<CategoryResponse> getAll() {
        return DtoConverter.convertToCategoryResponseList(categoryRepository.findAll());
    }
}
