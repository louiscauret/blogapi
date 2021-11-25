package com.blogapi.api.services

import com.blogapi.api.models.ArticleMessage
import com.blogapi.api.repositories.ArticleMessageRepository
import org.springframework.stereotype.Service

@Service
class ArticleMessageService(private val articleMessageRepository: ArticleMessageRepository) {
        fun save(message: ArticleMessage): ArticleMessage {
            return this.articleMessageRepository.save(message)
        }
}