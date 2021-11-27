package com.blogapi.api.repositories

import com.blogapi.api.models.Commentaries
import com.blogapi.api.models.Users
import org.springframework.data.repository.CrudRepository
import java.util.*

interface CommentariesRepository: CrudRepository<Commentaries, Int> {
    fun findByAuthor(author: Users): List<Commentaries?>
    fun findByCreationDate(date: Date): List<Commentaries?>
}