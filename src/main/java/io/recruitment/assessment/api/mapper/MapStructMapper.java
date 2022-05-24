package io.recruitment.assessment.api.mapper;

import io.recruitment.assessment.api.dto.authentication.AuthenticationResponse;
import io.recruitment.assessment.api.dto.news.NewsRequest;
import io.recruitment.assessment.api.dto.news.NewsResponse;
import io.recruitment.assessment.api.dto.order.CartResponse;
import io.recruitment.assessment.api.dto.order.ItemResponse;
import io.recruitment.assessment.api.dto.product.ProductRequest;
import io.recruitment.assessment.api.dto.product.ProductResponse;
import io.recruitment.assessment.api.model.Cart;
import io.recruitment.assessment.api.model.Item;
import io.recruitment.assessment.api.model.News;
import io.recruitment.assessment.api.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Map;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    ProductResponse productToProductDto(Product product);

    Product productDtoToProduct(ProductRequest productRequest);

    NewsResponse newsToNewsDto(News news);

    News newsDtoToNews(NewsRequest newsRequest);

    AuthenticationResponse toAuthDto(Map<String, String> map);

    CartResponse cartToCartDto(Cart cart);

    @Mapping(source = "product.id", target = "productID")
    ItemResponse itemToItemDto(Item item);
}
