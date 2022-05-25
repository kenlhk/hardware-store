package io.recruitment.assessment.api.service.impl;

import io.recruitment.assessment.api.model.Order;
import io.recruitment.assessment.api.model.User;
import io.recruitment.assessment.api.repository.OrderRepository;
import io.recruitment.assessment.api.service.AuthenticationService;
import io.recruitment.assessment.api.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AuthenticationService authenticationService;

    @Override
    public List<Order> findAll() {
        User user = authenticationService.getCurrentUser();
        return user.getOrders();
    }

    @Override
    public List<Order> addOrder(Order order) {
        return null;
    }
}
