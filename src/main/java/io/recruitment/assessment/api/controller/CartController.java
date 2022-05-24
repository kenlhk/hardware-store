package io.recruitment.assessment.api.controller;

import io.recruitment.assessment.api.dto.order.CartRequest;
import io.recruitment.assessment.api.dto.order.CartResponse;
import io.recruitment.assessment.api.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartMapper cartMapper;

    @PostMapping
    public ResponseEntity<CartResponse> addProduct(@RequestBody CartRequest request) {
        CartResponse response = cartMapper.addProduct(request);
        return ResponseEntity.ok(response);
    }
}
