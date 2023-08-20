package com.cyber009.webflex.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;


@Table(name = "articles")
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@ToString
@EqualsAndHashCode
public class Article {
    @Id
    private UUID id;
    private String title;
    private String image;
//    private List<String> tags;
    private String author;
    private LocalDateTime publishDateTime;
//    private List<String> contents;

}
