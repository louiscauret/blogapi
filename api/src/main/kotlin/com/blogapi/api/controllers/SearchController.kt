package com.blogapi.api.controllers

import com.blogapi.api.dtos.Message
import com.blogapi.api.services.ArticleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/search")
class SearchController {

    @Autowired
    lateinit var service: ArticleService

    /**
     * Get Artiles by Category or Date or FirstName of User
     */
    @GetMapping("article")
    fun getByCategory(@RequestParam category: String?,
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
}