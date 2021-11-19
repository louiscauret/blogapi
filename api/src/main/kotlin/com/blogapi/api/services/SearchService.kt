package com.blogapi.api.services

import com.blogapi.api.models.Article
import com.blogapi.api.repositories.ArticleRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class SearchService(private val articleRepository: ArticleRepository) {

    fun findByAuthor(author: String): List<Article?> {
        return this.articleRepository.findByAuthor(author)
    }

    fun findByCategory(category: String): List<Article?> {
        return this.articleRepository.findByCategory(category)
    }

    fun findByDate(date: Date): List<Article?> {
        return this.articleRepository.findByCreationDate(date)
    }

}