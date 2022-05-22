package io.recruitment.assessment.api.mapper;

import io.recruitment.assessment.api.dto.NewsRequest;
import io.recruitment.assessment.api.dto.NewsResponse;
import io.recruitment.assessment.api.dto.ProductRequest;
import io.recruitment.assessment.api.dto.ProductResponse;
import io.recruitment.assessment.api.model.News;
import io.recruitment.assessment.api.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    ProductResponse productToResponse(Product product);

    Product requestToProduct(ProductRequest productRequest);

    NewsResponse newsToResponse(News news);

    News requestToNews(NewsRequest newsRequest);
}
