package com.blogapi.api.repositories

import com.blogapi.api.models.Commentaries
import com.blogapi.api.models.Users
import org.springframework.data.repository.CrudRepository

interface CommentariesRepository: CrudRepository<Commentaries, Int> {
    fun findByAuthor(author: Users): List<Commentaries?>
}