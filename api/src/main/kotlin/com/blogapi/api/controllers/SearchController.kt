package com.blogapi.api.controllers

import com.blogapi.api.dtos.ArticleDTO
import com.blogapi.api.dtos.SearchDTO
import com.blogapi.api.models.Article
import com.blogapi.api.services.ArticleService
import com.blogapi.api.services.SearchService
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.event
import java.text.SimpleDateFormat
import java.util.*


@RestController
@RequestMapping("api/search")
class SearchController(private val searchService: SearchService, private val articleService: ArticleService) {

    @PostMapping("create")
    fun create(@RequestBody body: ArticleDTO): ResponseEntity<Any> {
        val art = Article()
        art.author = body.author
        art.text = body.text
        art.title = body.title
        art.category = body.category
        art.creationDate = java.sql.Date(0L)

        return ResponseEntity.ok(this.articleService.save(art))
    }

    @GetMapping("author")
    fun searchByAuthor(@RequestBody body: SearchDTO) :ResponseEntity<Any>{
        return ResponseEntity.ok(this.searchService.findByAuthor(body.searchBy))
    }

    @GetMapping("category")
    fun searchByCategory(@RequestBody body: SearchDTO) :ResponseEntity<Any>{
        return ResponseEntity.ok(this.searchService.findByCategory(body.searchBy))
    }

    @GetMapping("date")
    fun searchByDate(@RequestBody body: SearchDTO) :ResponseEntity<List<Article?>>{
        return ResponseEntity.ok(this.searchService.findByDate(SimpleDateFormat("dd-MM-yyyy").parse(body.searchBy)))
    }
}