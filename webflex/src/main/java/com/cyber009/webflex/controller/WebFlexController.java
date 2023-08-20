package com.cyber009.webflex.controller;

import com.cyber009.webflex.dto.ArticleDto;
import com.cyber009.webflex.entity.Article;
import com.cyber009.webflex.repository.ArticleRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class WebFlexController {

    private final ArticleRepository articleRepository;

    private final List<Article> articleList;


    @Autowired
    public WebFlexController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
        articleList = new LinkedList<>();

    }

    @GetMapping("/{id}")
    public Mono<ArticleDto> findById(@PathVariable() UUID id) {
        return Mono.empty();// .justOrEmpty(articleDtoList.stream().filter(articleDto -> articleDto.getId().equals(id)).findFirst().orElse(null));
    }

    @GetMapping
    public Flux<Article> findAll(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        if(pageNo < 1) pageNo = 1;
        if(pageSize < 1) pageSize = 10;
        Pageable page = PageRequest.of(0, 10);
        return articleRepository.findAll();
//        return Flux.fromIterable(articleDtoList.stream().skip((pageNo-1)*pageSize).limit(pageSize).collect(Collectors.toList()));
    }



    private Flux<Article> generate() {
        Faker faker = new Faker();
        for(int i=0; i<10000; i++) {
            int year = faker.number().numberBetween(1270, 1941);  // Example range
            int month = faker.number().numberBetween(1, 13);     // 1-12
            int day = faker.number().numberBetween(1, 29);       // 1-28/29
            int hour = faker.number().numberBetween(0, 24);      // 0-23
            int minute = faker.number().numberBetween(0, 60);    // 0-59
            int second = faker.number().numberBetween(0, 60);
            LocalDateTime randomDateTime = LocalDateTime.of(year, month, day, hour, minute, 0);
            randomDateTime.atZone(ZoneOffset.UTC);
            articleList.add(Article.builder()
                    .id(UUID.randomUUID().toString())
                    .title(faker.book().title())
                    .image(faker.internet().image())
//                    .tags(Arrays.stream(new String[] { faker.internet().slug(), faker.internet().slug() }).toList())
//                    .author(faker.book().author())
//                    .publishDateTime(randomDateTime)
//                    .contents(faker.lorem().paragraphs(faker.number().numberBetween(26, 35)))
                    .build()
            );
        }
        Flux<Article> list = articleRepository.saveAll(articleList);
        return list;
    }

}
