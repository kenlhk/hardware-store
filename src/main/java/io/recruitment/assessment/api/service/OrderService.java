package io.recruitment.assessment.api.service;

import io.recruitment.assessment.api.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();

    Order findById(Long orderId);
}
