package io.recruitment.assessment.api.mapper;

import io.recruitment.assessment.api.dto.order.OrderResponse;
import io.recruitment.assessment.api.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final MapStructMapper mapper;
    private final OrderService orderService;

    public List<OrderResponse> findAll() {
        return mapper.toOrderDto(orderService.findAll());
    }
}
