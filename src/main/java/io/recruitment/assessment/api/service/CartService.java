package io.recruitment.assessment.api.service;

import io.recruitment.assessment.api.model.Cart;

public interface CartService {
    Cart addProduct(Long productID, Long quality);
}
