package io.recruitment.assessment.api.mapper;

import io.recruitment.assessment.api.dto.product.ProductRequest;
import io.recruitment.assessment.api.dto.product.ProductResponse;
import io.recruitment.assessment.api.model.Product;
import io.recruitment.assessment.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final MapStructMapper mapper;
    private final ProductService productService;


    public Page<ProductResponse> findAll(String search, Pageable pageable) {
        Page<Product> productPage = productService.findAll(search, pageable);
        Page<ProductResponse> response = productPage.map(mapper::productToProductDto);
        return response;
    }

    public ProductResponse findById(Long id){
        Product product = productService.findById(id);
        return mapper.productToProductDto(product);
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = mapper.productDtoToProduct(productRequest);
        return mapper.productToProductDto(productService.createProduct(product));
    }

    public ProductResponse updateProduct(ProductRequest productRequest, Long productID) {
        Product product = mapper.productDtoToProduct(productRequest);
        product.setId(productID);
        return mapper.productToProductDto(productService.updateProduct(product, productID));
    }

    public void deleteProduct(Long productID) {
        productService.deleteProduct(productID);
    }
}
