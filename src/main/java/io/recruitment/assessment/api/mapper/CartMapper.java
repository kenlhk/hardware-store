package io.recruitment.assessment.api.mapper;

import io.recruitment.assessment.api.dto.order.CartRequest;
import io.recruitment.assessment.api.dto.order.CartResponse;
import io.recruitment.assessment.api.model.Cart;
import io.recruitment.assessment.api.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartMapper {

    private final MapStructMapper mapper;
    private final CartService cartService;

    public CartResponse addProduct(CartRequest request){
        Cart cart = cartService.addProduct(request.getProductID(), request.getQuantity());
        return mapper.cartToCartDto(cart);
    }


}
