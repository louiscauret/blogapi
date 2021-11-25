package com.blogapi.api.services

import com.blogapi.api.models.Users
import com.blogapi.api.repositories.UserRepository
import org.springframework.stereotype.Service

@Service

class UserService(private val userRepository: UserRepository) {
    fun save(user: Users): Users {
        return this.userRepository.save(user)
    }

    fun findByEmail(email: String): Users? {
        return this.userRepository.findByEmail(email)
    }

    fun getById(id: Int): Users {
        return this.userRepository.getById(id)
    }
}