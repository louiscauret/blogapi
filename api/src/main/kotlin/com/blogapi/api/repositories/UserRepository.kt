package com.blogapi.api.repositories

import com.blogapi.api.models.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<Users, Int> {
    fun findByEmail(email: String): Users?
}