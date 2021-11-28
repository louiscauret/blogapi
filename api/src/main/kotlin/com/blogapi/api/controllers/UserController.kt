package com.blogapi.api.controllers

import com.blogapi.api.dtos.RegisterDTO
import com.blogapi.api.dtos.UpdateUsersDTO
import com.blogapi.api.models.Users
import com.blogapi.api.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController {

    @Autowired
    lateinit var service: UserService

    /**
     * Create User
     */
    @PostMapping
    fun create(@RequestBody body: RegisterDTO): ResponseEntity<Users> {
        return ResponseEntity.ok(service.save(body))
    }

    /**
     * Get All Users
     */
    @GetMapping
    fun getAll(): ResponseEntity<MutableIterable<Users>> {
        return ResponseEntity.ok(service.findAll())
    }

    /**
     * Get User by ID
     */
    @GetMapping("/{userId}")
    fun getById(@PathVariable userId:Int): ResponseEntity<Users?> {
        return ResponseEntity.ok(this.service.getById(userId))
    }

    /**
     * Update User by ID
     */
    @PutMapping
    fun update(@RequestBody body: UpdateUsersDTO): ResponseEntity<Users> {
        return ResponseEntity.ok(service.updateUserEmail(body.id, body.email))
    }

    /**
     * Delete User by ID
     */
    @DeleteMapping("/{userId}")
    fun delete(@PathVariable userId: Int): ResponseEntity<Any> {
        return ResponseEntity.ok(service.delete(userId))
    }
}