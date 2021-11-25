package com.blogapi.api.repositories

import com.blogapi.api.models.Articles
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ArticleRepository: JpaRepository<Articles, Int> {
    fun findByAuthor(author: String): List<Articles?>
    fun findByCategory(category: String): List<Articles?>
    fun findByCreationDate(date: Date): List<Articles?>
}