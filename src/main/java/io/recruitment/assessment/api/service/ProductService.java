package io.recruitment.assessment.api.service;

import io.recruitment.assessment.api.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Page<Product> findAll(String search, Pageable pageable);

    Product createProduct(Product product);

    List<Product> findAllProducts();

    Product updateProduct(Product product, Long id);

    void deleteProduct(Long id);
}
