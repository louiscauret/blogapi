package com.blogapi.api.controllers

import com.blogapi.api.dtos.ArticleDTO
import com.blogapi.api.models.Article
import com.blogapi.api.services.ArticleService
import com.blogapi.api.services.UserService
import io.jsonwebtoken.Jwts
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/articles")
class ArticleController(private val articleService: ArticleService, private val userService: UserService) {

    @PostMapping("create")
    fun create(@RequestBody body: ArticleDTO, @CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        val art = Article()
        val bodyJwt = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).body
        val user = this.userService.getById(bodyJwt.issuer.toInt())
        art.author = user
        art.text = body.text
        art.title = body.title
        art.category = body.category
        art.creationDate = java.sql.Date(0L)

        return ResponseEntity.ok(this.articleService.save(art))
    }

}