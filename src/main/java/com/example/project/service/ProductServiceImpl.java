package com.example.project.service;

import com.example.project.converter.DtoConverter;
import com.example.project.dto.ProductResponse;
import com.example.project.entity.Product;
import com.example.project.exceptions.EcommerceException;
import com.example.project.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    @Override
    public Product finById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return productOptional.get();
        }
        throw new EcommerceException("The product with given id does not exist. ID: " + id, HttpStatus.NOT_FOUND);
    }
    @Override
    public ProductResponse save(Product product) {
        return DtoConverter.convertToProductResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse getById(Long id) {
        return DtoConverter.convertToProductResponse(finById(id));
    }

    @Override
    public ProductResponse update(Long id, Product product) {
        Product willUpdateProduct = finById(id);
        willUpdateProduct.setName(product.getName());
        willUpdateProduct.setPrice(product.getPrice());
        willUpdateProduct.setStock(product.getStock());
        return DtoConverter.convertToProductResponse(productRepository.save(willUpdateProduct));
    }

    @Override
    public ProductResponse delete(Long id) {
        Product willDeleteProduct = finById(id);
        productRepository.delete(willDeleteProduct);
        return DtoConverter.convertToProductResponse(willDeleteProduct);
    }

    @Override
    public List<ProductResponse> getAll() {
        return DtoConverter.convertToProductResponseList(productRepository.findAll());
    }

 @Override
    public List<ProductResponse> getProductsByGender(String gender) {
       List<Product> products = productRepository.findByCategoryGender(gender.toLowerCase());
     if(products.isEmpty()){
         throw new EcommerceException("I could not found product by this "+gender,HttpStatus.NOT_FOUND);
     }
       return DtoConverter.convertToProductResponseList(products);
    }

    @Override
    public List<ProductResponse> getProductsByName(String name) {
        List<Product> products = productRepository.findByCategoryName(name.toLowerCase());
        if(products.isEmpty()){
            throw new EcommerceException("I could not found product by this "+name,HttpStatus.NOT_FOUND);
        }
        return DtoConverter.convertToProductResponseList(products);
    }

    @Override
    public List<ProductResponse> getProductsByNameAndGender(String name, String gender) {
        List<Product> products = productRepository.findByCategoryNameAndGender(name.toLowerCase(),gender.toLowerCase());
        if(products.isEmpty()){
            throw new EcommerceException("I could not found products this" +name + "or" + gender , HttpStatus.NOT_FOUND);
        }
        return DtoConverter.convertToProductResponseList(products);
    }
}
