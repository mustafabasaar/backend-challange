package com.example.project.converter;

import com.example.project.dto.LoginUserResponse;
import com.example.project.entity.AppUser;

import java.util.ArrayList;
import java.util.List;

public class DtoConverter {
    /*
    public static CategoryResponse convertToCategoryResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getTitle(),
                category.getGender(),
                category.getCode(),
                category.getRating(),
                category.getImg());
    }

    public static List<CategoryResponse> convertToCategoryResponseList(List<Category> categories) {
        List<CategoryResponse> responses = new ArrayList<>();
        categories.forEach(category -> responses.add(new CategoryResponse(
                category.getId(),
                category.getTitle(),
                category.getGender(),
                category.getCode(),
                category.getRating(),
                category.getImg()
        )));
        return responses;
    }

    public static ProductResponse convertToProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                DtoConverter.convertToImagesObjResponseList(product.getImages()),
                product.getPrice(),
                product.getRating(),
                product.getStock(),
                product.getSellCount(),
                product.getCategory().getId(),
                product.getStore().getId()
        );
    }

    public static List<ProductResponse> convertToProductResponseList(List<Product> products) {
        List<ProductResponse> responses = new ArrayList<>();
        products.forEach(product -> responses.add(new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                DtoConverter.convertToImagesObjResponseList(product.getImages()),
                product.getPrice(),
                product.getRating(),
                product.getStock(),
                product.getSellCount(),
                product.getCategory().getId(),
                product.getStore().getId()
        )));
        return responses;
    }

*/
    public static LoginUserResponse convertToLoginUserResponse(AppUser user, String roleId){
        return new LoginUserResponse(user.getEmail(), user.getFirstName(), roleId);
    }
}
