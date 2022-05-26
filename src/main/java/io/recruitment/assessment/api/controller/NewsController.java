package io.recruitment.assessment.api.controller;

import io.recruitment.assessment.api.dto.news.NewsRequest;
import io.recruitment.assessment.api.dto.news.NewsResponse;
import io.recruitment.assessment.api.mapper.NewsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsMapper newsMapper;

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<NewsResponse> createNews(@RequestBody @Validated NewsRequest request) {
        NewsResponse response = newsMapper.createNews(request);
        return ResponseEntity.ok(response);
    }
}
