package com.blogapi.api.repositories

import com.blogapi.api.models.Commentaries
import org.springframework.data.jpa.repository.JpaRepository

interface CommentariesRepository: JpaRepository<Commentaries, Int> {
}