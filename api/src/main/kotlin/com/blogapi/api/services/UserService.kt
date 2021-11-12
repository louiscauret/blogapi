package com.blogapi.api.services

import com.blogapi.api.models.User
import com.blogapi.api.repositories.UserRepository
import org.springframework.stereotype.Service

@Service

class UserService(private val userRepository: UserRepository) {
    fun save(user: User): User {
        return this.userRepository.save(user)
    }

    fun findByEmail(email: String): User? {
        return this.userRepository.findByEmail(email)
    }

    fun getById(id: Int): User {
        return this.userRepository.getById(id)
    }
}