package com.blogapi.api.controllers

import com.blogapi.api.dtos.LoginDTO
import com.blogapi.api.dtos.Message
import com.blogapi.api.dtos.RegisterDTO
import com.blogapi.api.dtos.UpdateUsersDTO
import com.blogapi.api.models.Users
import com.blogapi.api.services.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("api/users")
class AuthController(private val userService: UserService) {

    /**
     * Create User
     */
    @PostMapping("register")
    fun register(@RequestBody body: RegisterDTO): ResponseEntity<Users>{
        val user = Users()
        user.firstName = body.firstName
        user.lastName = body.lastName
        user.email = body.email
        user.password = body.password
        user.avatar = "${body.firstName}+${body.lastName}"

        return ResponseEntity.ok(this.userService.save(user))
    }

    /**
     * Get All Users
     */
    @GetMapping
    fun getAll(): ResponseEntity<MutableIterable<Users>> {
        return ResponseEntity.ok(this.userService.findAll())
    }

    /**
     * Get User by ID
     */
    @GetMapping("/{userId}")
    fun getById(@PathVariable userId:Int): ResponseEntity<Users?> {
        return ResponseEntity.ok(this.userService.getById(userId))
    }

    /**
     * Update User by ID
     */
    @PutMapping
    fun update(@RequestBody body: UpdateUsersDTO): ResponseEntity<Users> {
        return ResponseEntity.ok(this.userService.updateUserEmail(body.id, body.email))
    }

    /**
     * Delete User by ID
     */
    @DeleteMapping("/{userId}")
    fun delete(@PathVariable userId: Int): ResponseEntity<Any> {
        return ResponseEntity.ok(this.userService.delete(userId))
    }


    @PostMapping("login")
    fun login(@RequestBody body: LoginDTO, response: HttpServletResponse): ResponseEntity<Any> {
        val user = this.userService.findByEmail(body.email)
                ?: return ResponseEntity.badRequest().body(Message("User not found"))

        if (!user.comparePassword(body.password)){
            return ResponseEntity.badRequest().body(Message("Invalid Password"))
        }

        val issuer = user.idUser.toString()

        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000)) // 1 jour
            .signWith(SignatureAlgorithm.HS512, "secret").compact()

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true

        response.addCookie(cookie )

        return ResponseEntity.ok(Message("Success"))
    }

    //authentification
    @GetMapping("user")
    fun user(@CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        try {
            if (jwt == null) {
                return ResponseEntity.status(401).body(Message("Unauthenticated"))
            }

            val body = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).body

            return ResponseEntity.ok(this.userService.getById(body.issuer.toInt()))
        } catch (e: Exception){
            return ResponseEntity.status(401).body(Message("Unauthenticated"))
        }
    }

    @PostMapping("logout")
    fun logout(response: HttpServletResponse): ResponseEntity<Any> {
        val cookie = Cookie("jwt", "")
        cookie.maxAge = 0

        response.addCookie(cookie)

        return ResponseEntity.ok(Message("Logout Successfull"))
    }
}