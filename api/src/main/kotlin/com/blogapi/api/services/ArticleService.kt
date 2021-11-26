package com.blogapi.api.services

import com.blogapi.api.models.Articles
import com.blogapi.api.repositories.ArticleRepository
import com.blogapi.api.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class ArticleService {

    @Autowired
    lateinit var repository: ArticleRepository

    @Autowired
    lateinit var repositoryUser: UserRepository

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

    fun getByCategory(category: String?): List<Articles?>? {
        return category?.let { repository.findByCategory(it) }
    }

    fun getByDate(date: String?): List<Articles?> {
        val mDate = SimpleDateFormat("dd-MM-yyyy").parse(date)
        return repository.findByCreationDate(mDate)
    }

    fun getByAuthor(name: String?): List<Articles?>? {
        val mUser = name?.let { repositoryUser.findByFirstName(it) }
        return mUser?.let { repository.findByAuthor(it) }
    }
}