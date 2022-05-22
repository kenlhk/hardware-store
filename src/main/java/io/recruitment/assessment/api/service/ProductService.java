package io.recruitment.assessment.api.service;

import io.recruitment.assessment.api.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    List<Product> findAllProducts();

    Product updateProduct(Product product, Long id);

    void deleteProduct(Long id);
}
