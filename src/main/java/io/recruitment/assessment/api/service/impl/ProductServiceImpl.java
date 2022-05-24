package io.recruitment.assessment.api.service.impl;

import io.recruitment.assessment.api.exception.ApiRequestException;
import io.recruitment.assessment.api.model.Product;
import io.recruitment.assessment.api.repository.ProductRepository;
import io.recruitment.assessment.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAllProducts() {
        return null;
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        if (productRepository.findById(id).isEmpty()) {
            throw new ApiRequestException("Product not found.", HttpStatus.NOT_FOUND);
        }
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product productToDelete = productRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Product not found.", HttpStatus.NOT_FOUND));
        productRepository.delete(productToDelete);
    }
}
