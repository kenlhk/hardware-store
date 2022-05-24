package io.recruitment.assessment.api.dto.product;

import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private String defaultImage;
    private List<String> images;
    private Double price;
    private Double discount;
}
