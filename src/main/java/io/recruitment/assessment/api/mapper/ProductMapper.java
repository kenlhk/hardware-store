package io.recruitment.assessment.api.mapper;

import io.recruitment.assessment.api.dto.product.ProductRequest;
import io.recruitment.assessment.api.dto.product.ProductResponse;
import io.recruitment.assessment.api.model.Product;
import io.recruitment.assessment.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final MapStructMapper mapper;
    private final ProductService productService;


    public Page<ProductResponse> findByPage(int page, int size){
        Page<Product> productPage = productService.findByPage(page, size);
        Page<ProductResponse> response = productPage.map(mapper::productToProductDto);
        return response;
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
