package com.blogapi.api.controllers

import com.blogapi.api.dtos.CommentariesDTO
import com.blogapi.api.models.Commentaries
import com.blogapi.api.services.CommentariesService
import com.blogapi.api.services.ArticleService
import com.blogapi.api.services.UserService
import io.jsonwebtoken.Jwts
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/article_message")
class CommentariesController(private val articleMessage: CommentariesService, private val userService: UserService, private val articleService: ArticleService) {

    @PostMapping("create")
    fun create(@RequestBody body: CommentariesDTO, @CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        val com = Commentaries()
        val bodyJwt = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).body
        val user = this.userService.getById(bodyJwt.issuer.toInt())
        val article = this.articleService.getById(body.articleId)
        com.text = body.text
        com.author = user
        com.article = article
        com.creationDate = java.sql.Date(0L)

        return ResponseEntity.ok(this.articleMessage.save(com))
    }
}