package com.blogapi.api.controllers

import com.blogapi.api.dtos.Message
import com.blogapi.api.services.ArticleService
import com.blogapi.api.services.CommentariesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/search")
class SearchController {

    @Autowired
    lateinit var service: ArticleService

    @Autowired
    lateinit var commentariesService: CommentariesService

    /**
     * Get Articles by Category or Date or FirstName of User
     */
    @GetMapping("article")
    fun searchArticles(@RequestParam category: String?,
                      @RequestParam date: String?,
                      @RequestParam name: String?): ResponseEntity<Any> {
        return if (category != null) {
            ResponseEntity.ok(service.getByCategory(category))
        } else if (date != null) {
            ResponseEntity.ok(service.getByDate(date))
        } else if (name != null) {
            ResponseEntity.ok(service.getByAuthor(name))
        } else
            ResponseEntity.ok(Message("Need Params"))
    }

    /**
     * Get Comment by FirstName of User
     */
    @GetMapping("comment")
    fun searchCommentaries(@RequestParam name: String?,
                           @RequestParam date: String?): ResponseEntity<Any> {
        return if (name != null) {
            ResponseEntity.ok(commentariesService.getByAuthor(name))
        } else if (date != null) {
            ResponseEntity.ok(commentariesService.getByDate(date))
        } else
            ResponseEntity.ok(Message("Need Params"))
    }
}