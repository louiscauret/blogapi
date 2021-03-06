package com.blogapi.api.services

import com.blogapi.api.dtos.RegisterDTO
import com.blogapi.api.models.Users
import com.blogapi.api.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service

class UserService {

    @Autowired
    lateinit var repository: UserRepository

    fun save(body: RegisterDTO): Users {
        val user = Users()
        user.firstName = body.firstName
        user.lastName = body.lastName
        user.email = body.email
        user.password = body.password
        user.avatar = "${body.firstName}+${body.lastName}"

        return repository.save(user)
    }

    fun findAll(): MutableIterable<Users> {
        return repository.findAll()
    }

    fun getById(id: Int): Users? {
        return repository.findByIdOrNull(id)
    }

    fun findByEmail(email: String): Users? {
        return repository.findByEmail(email)
    }

    fun updateUserEmail(id: Int, email: String): Users? {
        val user: Users? = getById(id)
        user?.email = email
        return repository.save(user!!)
    }

    fun delete(id: Int) {
        repository.deleteById(id)
    }
}