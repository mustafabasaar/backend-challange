package com.example.project.controller;
import com.example.project.dto.CategoryResponse;
import com.example.project.entity.Category;
import com.example.project.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    @PostMapping("/")
    public CategoryResponse save(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @GetMapping("/{id}")
    public CategoryResponse getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }


    @PutMapping("/{id}")
    public CategoryResponse update(@PathVariable Long id, @RequestBody Category category){
        return categoryService.update(id,category);
    }
    @DeleteMapping("/{id}")
    public CategoryResponse delete(@PathVariable Long id){
        return categoryService.delete(id);
    }
    @GetMapping("/")
    public List<CategoryResponse> getAll() {
        return categoryService.getAll();
    }
}
