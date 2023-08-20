package com.cyber009.webflex.controller;

import com.cyber009.webflex.dto.ArticleDto;
import com.github.javafaker.Faker;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class WebFlexController {

    private final List<ArticleDto> articleDtoList;
    public WebFlexController() {
        articleDtoList = new LinkedList<>();
        generate();
    }
    @GetMapping
    public List<ArticleDto> findAll(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        if(pageNo < 1) pageNo = 1;
        if(pageSize < 1) pageSize = 10;

        return articleDtoList.stream().skip((pageNo-1)*pageSize).limit(pageSize).toList();
    }

    @GetMapping("/{id}")
    public ArticleDto findById(@PathVariable UUID id) {
        return articleDtoList.stream().filter(articleDto -> articleDto.getId().equals(id)).findFirst().orElse(null);
    }

    private List<ArticleDto> generate() {
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
            articleDtoList.add(ArticleDto.builder()
                    .id(UUID.randomUUID())
                    .title(faker.book().title())
                    .image(faker.internet().image())
                    .tags(Arrays.stream(new String[] { faker.internet().slug(), faker.internet().slug() }).toList())
                    .author(faker.book().author())
                    .publishDateTime(randomDateTime)
                    .contents(faker.lorem().paragraphs(faker.number().numberBetween(26, 35)))
                    .build()
            );
        }
        return articleDtoList;
    }

}
