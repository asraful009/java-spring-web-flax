package com.cyber009.webflex.dto;


import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@SuperBuilder
public class ArticleDto {
    private UUID id;
    private String title;
    private String image;
    private List<String> tags;
    private String author;
    private LocalDateTime publishDateTime;
    private List<String> contents;;
}
