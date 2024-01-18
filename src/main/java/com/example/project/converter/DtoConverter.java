package com.example.project.converter;

import com.example.project.dto.CategoryResponse;
import com.example.project.dto.LoginUserResponse;
import com.example.project.dto.ProductResponse;
import com.example.project.entity.AppUser;
import com.example.project.entity.Category;
import com.example.project.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class DtoConverter {

    public static CategoryResponse convertToCategoryResponse(Category category) {
        return new CategoryResponse(
                category.getTitle(),
                category.getGender());

    }

        public static List<CategoryResponse> convertToCategoryResponseList(List<Category> categories) {
            List<CategoryResponse> responses = new ArrayList<>();
            categories.forEach(category -> responses.add(new CategoryResponse(
                    category.getTitle(),
                    category.getGender()

            )));
            return responses;
        }

    public static LoginUserResponse convertToLoginUserResponse(AppUser user, String roleId){
        return new LoginUserResponse(user.getEmail(), user.getFirstName(), roleId);
    }
    public static ProductResponse convertToProductResponse(Product product) {
        return new ProductResponse(
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getCategory().getId());
    }

    public static List<ProductResponse> convertToProductResponseList(List<Product> products) {
        List<ProductResponse> responses = new ArrayList<>();
        products.forEach(product -> responses.add(new ProductResponse(
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getCategory().getId())));
        return responses;
    }
}
