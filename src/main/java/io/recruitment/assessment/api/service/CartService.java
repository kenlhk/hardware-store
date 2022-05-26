package io.recruitment.assessment.api.service;

import io.recruitment.assessment.api.dto.order.CartResponse;
import io.recruitment.assessment.api.model.Cart;

public interface CartService {
    Cart findAll();

    Cart addProduct(Long productID, Long quality);
}
