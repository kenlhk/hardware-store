package io.recruitment.assessment.api.controller;

import io.recruitment.assessment.api.dto.ProductRequest;
import io.recruitment.assessment.api.dto.ProductResponse;
import io.recruitment.assessment.api.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductMapper productMapper;

    @PostMapping
    ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productMapper.createProduct(productRequest);
        return ResponseEntity.ok(productResponse);
    }

    @PutMapping("/{productID}")
    ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest, @PathVariable Long productID){
        ProductResponse productResponse = productMapper.updateProduct(productRequest, productID);
        return ResponseEntity.ok(productResponse);
    }

    @DeleteMapping("/{productID}")
    ResponseEntity deleteProduct(@PathVariable Long productID){
        productMapper.deleteProduct(productID);
        return ResponseEntity.noContent().build();
    }
}
