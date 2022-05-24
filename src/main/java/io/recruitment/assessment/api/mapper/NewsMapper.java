package io.recruitment.assessment.api.mapper;

import io.recruitment.assessment.api.dto.news.NewsRequest;
import io.recruitment.assessment.api.dto.news.NewsResponse;
import io.recruitment.assessment.api.model.News;
import io.recruitment.assessment.api.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewsMapper {

    private final MapStructMapper mapper;
    private final NewsService newsService;

    public NewsResponse createNews(NewsRequest newsRequest) {
        News news = mapper.newsDtoToNews(newsRequest);
        return mapper.newsToNewsDto(newsService.createNews(news));
    }
}
