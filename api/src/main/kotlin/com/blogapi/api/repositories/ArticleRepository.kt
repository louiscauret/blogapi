package com.blogapi.api.repositories

import com.blogapi.api.models.Articles
import com.blogapi.api.models.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import java.util.*

interface ArticleRepository: CrudRepository<Articles, Int> {
    fun findByAuthor(author: Users): List<Articles?>
    fun findByCategory(category: String): List<Articles?>
    fun findByCreationDate(date: Date): List<Articles?>
}