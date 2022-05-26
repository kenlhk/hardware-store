package io.recruitment.assessment.api.controller;

import io.recruitment.assessment.api.dto.order.CartRequest;
import io.recruitment.assessment.api.dto.order.CartResponse;
import io.recruitment.assessment.api.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartMapper cartMapper;

    @GetMapping
    public ResponseEntity<CartResponse> findAll() {
        CartResponse response = cartMapper.findAll();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CartResponse> addProduct(@RequestBody CartRequest request) {
        CartResponse response = cartMapper.addProduct(request);
        return ResponseEntity.ok(response);
    }
}
