package com.blogapi.api.controllers

import com.blogapi.api.dtos.LoginDTO
import com.blogapi.api.dtos.Message
import com.blogapi.api.dtos.RegisterDTO
import com.blogapi.api.dtos.UpdateUsersDTO
import com.blogapi.api.models.Users
import com.blogapi.api.services.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("api")
class AuthController {

    @Autowired
    lateinit var service: UserService

    @PostMapping("login")
    fun login(@RequestBody body: LoginDTO,
              response: HttpServletResponse): ResponseEntity<Any> {
        val user = this.service.findByEmail(body.email)
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

        response.addCookie(cookie)

        return ResponseEntity.ok(Message("Success"))
    }

    @PostMapping("logout")
    fun logout(response: HttpServletResponse): ResponseEntity<Any> {
        val cookie = Cookie("jwt", "")
        cookie.maxAge = 0

        response.addCookie(cookie)

        return ResponseEntity.ok(Message("Logout Successfull"))
    }
}