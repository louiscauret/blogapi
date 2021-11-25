package com.blogapi.api.controllers

import com.blogapi.api.dtos.ArticleMessageDTO
import com.blogapi.api.models.ArticleMessage
import com.blogapi.api.services.ArticleMessageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/article_message")
class ArticleMessageController(private val articleMessage: ArticleMessageService) {

    @PostMapping("create")
    fun create(@RequestBody body: ArticleMessageDTO): ResponseEntity<Any> {
        val com = ArticleMessage()
        com.author = body.author
        com.text = body.text
        com.creationDate = java.sql.Date(0L)

        return ResponseEntity.ok(this.articleMessage.save(com))
    }
}