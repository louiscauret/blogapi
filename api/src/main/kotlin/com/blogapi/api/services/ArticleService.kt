package com.blogapi.api.services

import com.blogapi.api.models.Article
import com.blogapi.api.repositories.ArticleRepository
import org.springframework.stereotype.Service

@Service
class ArticleService(private val articleRepository: ArticleRepository) {
    fun save(article: Article): Article {
        return this.articleRepository.save(article)
    }
}