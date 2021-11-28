package com.blogapi.api.controllers

import com.blogapi.api.dtos.ArticleDTO
import com.blogapi.api.dtos.UpdateArticlesDTO
import com.blogapi.api.dtos.UpdateUsersDTO
import com.blogapi.api.models.Articles
import com.blogapi.api.models.Users
import com.blogapi.api.services.ArticleService
import com.blogapi.api.services.UserService
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/articles")
class ArticleController {

    @Autowired
    lateinit var articleService: ArticleService

    /**
     * Get All Article
     */
    @GetMapping
    fun getAll(): ResponseEntity<MutableIterable<Articles>> {
        return ResponseEntity.ok(articleService.findAll())
    }

    /**
     * Get Article by ID
     */
    @GetMapping("/{articleId}")
    fun getById(@PathVariable articleId:Int): ResponseEntity<Articles?> {
        return ResponseEntity.ok(articleService.getById(articleId))
    }

    /**
     * Update Article by ID
     */
    @PutMapping
    fun update(@RequestBody body: UpdateArticlesDTO): ResponseEntity<Articles> {
        return ResponseEntity.ok(articleService.update(body.id, body.title, body.text, body.category))
    }

    /**
     * Delete Article by ID
     */
    @DeleteMapping("/{articleId}")
    fun delete(@PathVariable articleId: Int): ResponseEntity<Any> {
        return ResponseEntity.ok(articleService.delete(articleId))
    }

    /**
     * Create Article
     */
    @PostMapping()
    fun create(@RequestBody body: ArticleDTO,
               @CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        return ResponseEntity.ok(articleService.save(body, jwt))
    }

}