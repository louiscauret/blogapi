package com.blogapi.api.services

import com.blogapi.api.dtos.CommentariesDTO
import com.blogapi.api.models.Commentaries
import com.blogapi.api.repositories.CommentariesRepository
import com.blogapi.api.repositories.UserRepository
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat

@Service
class CommentariesService {

    @Autowired
    lateinit var repository: CommentariesRepository

    @Autowired
    lateinit var repositoryUser: UserRepository

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var articleService: ArticleService

    fun save(body: CommentariesDTO, jwt: String?): Commentaries {
        val com = Commentaries()
        val bodyJwt = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).body
        val user = userService.getById(bodyJwt.issuer.toInt())
        val article = articleService.getById(body.articleId)
        com.text = body.text
        com.author = user
        com.article = article
        com.creationDate = java.sql.Date(0L)

        return this.repository.save(com)
    }

    fun findAll(): MutableIterable<Commentaries> {
        return repository.findAll()
    }

    fun getById(articleId: Int): Commentaries? {
        return repository.findByIdOrNull(articleId)
    }

    fun delete(id: Int) {
        repository.deleteById(id)
    }

    fun update(id: Int, text: String?): Commentaries? {
        val comment: Commentaries? = getById(id)
        if (text != null) {
            comment?.text = text
        }
        return repository.save(comment!!)
    }

    fun getByAuthor(name: String?): List<Commentaries?>? {
        val mUser = name?.let { repositoryUser.findByFirstName(it) }
        return mUser?.let { repository.findByAuthor(it) }
    }

    fun getByDate(date: String?): List<Commentaries?>? {
        val mDate = SimpleDateFormat("dd-MM-yyyy").parse(date)
        return repository.findByCreationDate(mDate)
    }
}