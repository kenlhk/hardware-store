package io.recruitment.assessment.api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NewsResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDate date;
}
