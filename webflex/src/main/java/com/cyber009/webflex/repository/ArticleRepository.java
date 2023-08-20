package com.cyber009.webflex.repository;

import com.cyber009.webflex.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface ArticleRepository extends R2dbcRepository<Article, String> {

}
