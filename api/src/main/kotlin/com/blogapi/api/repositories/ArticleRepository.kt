package com.blogapi.api.repositories

import com.blogapi.api.models.Article
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ArticleRepository: JpaRepository<Article, Int> {
    fun findByAuthor(author: String): List<Article?>
    fun findByCategory(category: String): List<Article?>
    fun findByCreationDate(date: Date): List<Article?>
}