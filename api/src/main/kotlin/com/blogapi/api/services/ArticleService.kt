package com.blogapi.api.services

import com.blogapi.api.models.Articles
import com.blogapi.api.repositories.ArticleRepository
import org.springframework.stereotype.Service

@Service
class ArticleService(private val articleRepository: ArticleRepository) {
    fun save(article: Articles): Articles {
        return this.articleRepository.save(article)
    }

    fun getById(articleId: Int): Articles {
        return this.articleRepository.getById(articleId)
    }
}