package com.blogapi.api.services

import com.blogapi.api.models.Articles
import com.blogapi.api.repositories.ArticleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ArticleService {

    @Autowired
    lateinit var repository: ArticleRepository

    fun save(article: Articles): Articles {
        return repository.save(article)
    }

    fun getById(articleId: Int): Articles? {
        return repository.findByIdOrNull(articleId)
    }

    fun findAll(): MutableIterable<Articles> {
        return repository.findAll()
    }

    fun delete(id: Int) {
        repository.deleteById(id)
    }

    fun update(id: Int, title: String?, text: String?, category: String?): Articles? {
        val article: Articles? = getById(id)
        if (category != null) {
            article?.category = category
        }
        if (text != null) {
            article?.text = text
        }
        if (title != null) {
            article?.title = title
        }
        return repository.save(article!!)
    }
}