package io.recruitment.assessment.api.service.impl;

import io.recruitment.assessment.api.model.News;
import io.recruitment.assessment.api.repository.NewsRepository;
import io.recruitment.assessment.api.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Override
    public News createNews(News news) {
        news.setDate(LocalDate.now());
        return newsRepository.save(news);
    }
}
