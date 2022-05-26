package io.recruitment.assessment.api.service.impl;

import io.recruitment.assessment.api.exception.ApiRequestException;
import io.recruitment.assessment.api.model.Product;
import io.recruitment.assessment.api.repository.ProductRepository;
import io.recruitment.assessment.api.repository.specification.ProductSpecificationBuilder;
import io.recruitment.assessment.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Page<Product> findAll(String search, Pageable pageable) {
        ProductSpecificationBuilder builder = new ProductSpecificationBuilder();
        Specification<Product> specification = builder.parse(search).build();
        return productRepository.findAll(specification, pageable);
    }

    @Override
    public Product findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Product not found.", HttpStatus.NOT_FOUND));
        return product;
    }

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
