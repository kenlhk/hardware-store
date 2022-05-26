package io.recruitment.assessment.api.service.impl;

import io.recruitment.assessment.api.exception.ApiRequestException;
import io.recruitment.assessment.api.model.Cart;
import io.recruitment.assessment.api.model.Item;
import io.recruitment.assessment.api.model.Product;
import io.recruitment.assessment.api.model.User;
import io.recruitment.assessment.api.repository.CartRepository;
import io.recruitment.assessment.api.repository.ItemRepository;
import io.recruitment.assessment.api.repository.ProductRepository;
import io.recruitment.assessment.api.service.AuthenticationService;
import io.recruitment.assessment.api.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;
    private final AuthenticationService authenticationService;

    @Override
    public Cart findAll() {
        User user = authenticationService.getCurrentUser();
        return user.getCart();
    }

    @Override
    public Cart addProduct(Long productID, Long quality) {
        User user = authenticationService.getCurrentUser();
        Cart cart = user.getCart();
        Product product = productRepository.findById(productID)
                .orElseThrow(() -> new ApiRequestException("Product not found", HttpStatus.NOT_FOUND));
        Boolean exist = false;
        for (Item item : cart.getItems()) {
            if (item.getProduct().equals(product)) {
                item.setQuantity(item.getQuantity() + quality);
                itemRepository.save(item);
                exist = true;
                break;
            }
        }
        if (!exist) {
            Item item = new Item();
            item.setProduct(product);
            item.setQuantity(quality);
            itemRepository.save(item);
            cart.getItems().add(item);
        }
        cartRepository.save(cart);
        return cart;
    }
}
