package io.recruitment.assessment.api.dto.order;

import io.recruitment.assessment.api.model.Item;
import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private List<Item> items;
}
