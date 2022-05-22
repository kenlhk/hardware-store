package io.recruitment.assessment.api.mapper;

import io.recruitment.assessment.api.dto.ProductRequest;
import io.recruitment.assessment.api.dto.ProductResponse;
import io.recruitment.assessment.api.model.Product;
import io.recruitment.assessment.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final MapStructMapper mapper;
    private final ProductService productService;

    public ProductResponse createProduct(ProductRequest productRequest){
        Product product = mapper.requestToProduct(productRequest);
        return mapper.productToResponse(productService.createProduct(product));
    }

    public ProductResponse updateProduct(ProductRequest productRequest, Long productID){
        Product product = mapper.requestToProduct(productRequest);
        product.setId(productID);
        return mapper.productToResponse(productService.updateProduct(product, productID));
    }

    public void deleteProduct(Long productID){
        productService.deleteProduct(productID);
    }
}
