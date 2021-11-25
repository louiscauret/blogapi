package com.blogapi.api.services

import com.blogapi.api.models.Commentaries
import com.blogapi.api.repositories.CommentariesRepository
import org.springframework.stereotype.Service

@Service
class CommentariesService(private val commentariesRepository: CommentariesRepository) {
        fun save(message: Commentaries): Commentaries {
            return this.commentariesRepository.save(message)
        }
}