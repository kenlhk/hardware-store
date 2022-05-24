package io.recruitment.assessment.api.controller;

import io.recruitment.assessment.api.dto.product.ProductRequest;
import io.recruitment.assessment.api.dto.product.ProductResponse;
import io.recruitment.assessment.api.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductMapper productMapper;

    @GetMapping("/test")
    String test(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        ProductResponse response = productMapper.createProduct(request);
        return ResponseEntity.ok(response);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{productID}")
    ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest request, @PathVariable Long productID) {
        ProductResponse response = productMapper.updateProduct(request, productID);
        return ResponseEntity.ok(response);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{productID}")
    ResponseEntity deleteProduct(@PathVariable Long productID) {
        productMapper.deleteProduct(productID);
        return ResponseEntity.noContent().build();
    }
}
