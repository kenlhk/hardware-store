package io.recruitment.assessment.api.controller;

import io.recruitment.assessment.api.dto.order.OrderResponse;
import io.recruitment.assessment.api.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderMapper orderMapper;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        List<OrderResponse> response = orderMapper.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Long orderId) {
        OrderResponse response = orderMapper.findById(orderId);
        return ResponseEntity.ok(response);
    }
}
