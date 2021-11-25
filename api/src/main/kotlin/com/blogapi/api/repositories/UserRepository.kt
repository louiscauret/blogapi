package com.blogapi.api.repositories

import com.blogapi.api.models.Users
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<Users, Int> {
    fun findByEmail(email: String): Users?
}