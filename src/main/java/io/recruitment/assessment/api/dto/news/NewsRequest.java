package io.recruitment.assessment.api.dto.news;

import lombok.Data;

@Data
public class NewsRequest {
    private String title;
    private String content;
}
