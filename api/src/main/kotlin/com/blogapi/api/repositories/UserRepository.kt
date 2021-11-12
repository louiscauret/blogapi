package com.blogapi.api.repositories

import com.blogapi.api.models.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Int> {
    fun findByEmail(email: String): User?
}