package io.recruitment.assessment.api.dto.order;

import lombok.Data;

@Data
public class CartRequest {
    private Long productID;
    private Long quantity;
}
