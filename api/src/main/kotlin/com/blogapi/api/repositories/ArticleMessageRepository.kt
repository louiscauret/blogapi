package com.blogapi.api.repositories

import com.blogapi.api.models.ArticleMessage
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleMessageRepository: JpaRepository<ArticleMessage, Int> {
}