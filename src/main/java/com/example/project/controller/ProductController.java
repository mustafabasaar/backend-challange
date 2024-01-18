package com.example.project.controller;
import com.example.project.dto.ProductResponse;
import com.example.project.entity.Product;
import com.example.project.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @PostMapping
    public ProductResponse save(@RequestBody Product product) {
        return productService.save(product);
    }
    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable Long id) {
        return productService.getById(id);
    }
    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable Long id, @RequestBody Product product) {
        return productService.update(id, product);
    }
    @DeleteMapping("/{id}")
    public ProductResponse delete(@PathVariable Long id) {
        return productService.delete(id);
    }
    @GetMapping("/")
    public List<ProductResponse> getAll() {
        return productService.getAll();
    }
}
