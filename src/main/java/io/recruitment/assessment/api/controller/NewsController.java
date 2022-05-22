package io.recruitment.assessment.api.controller;

import io.recruitment.assessment.api.dto.NewsRequest;
import io.recruitment.assessment.api.dto.NewsResponse;
import io.recruitment.assessment.api.mapper.NewsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsMapper newsMapper;

    @PostMapping
    public ResponseEntity<NewsResponse> createNews(@RequestBody NewsRequest newsRequest){
        NewsResponse newsResponse = newsMapper.createNews(newsRequest);
        return ResponseEntity.ok(newsResponse);
    }
}
