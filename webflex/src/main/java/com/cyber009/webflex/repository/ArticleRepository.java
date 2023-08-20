package com.cyber009.webflex.repository;

import com.cyber009.webflex.entity.Article;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArticleRepository extends ReactiveCrudRepository<Article, UUID> {
}
