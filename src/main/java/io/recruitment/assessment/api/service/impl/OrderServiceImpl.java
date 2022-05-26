package io.recruitment.assessment.api.service.impl;

import io.recruitment.assessment.api.exception.ApiRequestException;
import io.recruitment.assessment.api.model.Order;
import io.recruitment.assessment.api.model.User;
import io.recruitment.assessment.api.repository.OrderRepository;
import io.recruitment.assessment.api.service.AuthenticationService;
import io.recruitment.assessment.api.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public Order findById(Long orderId) {
        User user = authenticationService.getCurrentUser();
        List<Order> orders = user.getOrders();
        for(Order order: orders){
            if(order.getId() == orderId){
                return order;
            }
        }
        throw new ApiRequestException("Order not found.", HttpStatus.NOT_FOUND);
    }
}
