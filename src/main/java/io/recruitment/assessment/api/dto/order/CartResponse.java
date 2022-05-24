package io.recruitment.assessment.api.dto.order;

import lombok.Data;

import java.util.List;

@Data
public class CartResponse {
    private Long id;
    private List<ItemResponse> items;
}
