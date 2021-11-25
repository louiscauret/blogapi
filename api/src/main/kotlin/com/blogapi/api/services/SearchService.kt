package com.blogapi.api.services

import com.blogapi.api.models.Articles
import com.blogapi.api.repositories.ArticleRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class SearchService(private val articleRepository: ArticleRepository) {

    fun findByAuthor(author: String): List<Articles?> {
        return this.articleRepository.findByAuthor(author)
    }

    fun findByCategory(category: String): List<Articles?> {
        return this.articleRepository.findByCategory(category)
    }

    fun findByDate(date: Date): List<Articles?> {
        return this.articleRepository.findByCreationDate(date)
    }

}