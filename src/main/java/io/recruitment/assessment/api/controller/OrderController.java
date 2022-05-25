package io.recruitment.assessment.api.controller;

import io.recruitment.assessment.api.dto.order.OrderResponse;
import io.recruitment.assessment.api.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderMapper orderMapper;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        List<OrderResponse> response = orderMapper.findAll();
        return ResponseEntity.ok(response);
    }
}
