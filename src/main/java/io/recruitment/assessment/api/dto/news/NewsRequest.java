package io.recruitment.assessment.api.dto.news;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class NewsRequest {
    private String title;
    private String content;
}
