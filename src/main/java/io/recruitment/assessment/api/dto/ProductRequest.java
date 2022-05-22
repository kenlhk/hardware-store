package io.recruitment.assessment.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private String defaultImage;
    private List<String> images;
    private Double price;
    private Double discount;
}
