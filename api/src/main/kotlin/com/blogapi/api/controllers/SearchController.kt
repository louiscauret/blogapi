package com.blogapi.api.controllers

import com.blogapi.api.dtos.SearchDTO
import com.blogapi.api.models.Articles
import com.blogapi.api.services.SearchService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat

@RestController
@RequestMapping("api/search")
class SearchController(private val searchService: SearchService) {

    @GetMapping("author")
    fun searchByAuthor(@RequestBody body: SearchDTO) :ResponseEntity<Any>{
        return ResponseEntity.ok(this.searchService.findByAuthor(body.searchBy))
    }

    @GetMapping("category")
    fun searchByCategory(@RequestBody body: SearchDTO) :ResponseEntity<Any>{
        return ResponseEntity.ok(this.searchService.findByCategory(body.searchBy))
    }

    @GetMapping("date")
    fun searchByDate(@RequestBody body: SearchDTO) :ResponseEntity<List<Articles?>>{
        return ResponseEntity.ok(this.searchService.findByDate(SimpleDateFormat("dd-MM-yyyy").parse(body.searchBy)))
    }
}