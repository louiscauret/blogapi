package com.blogapi.api.services

import com.blogapi.api.models.Commentaries
import com.blogapi.api.repositories.CommentariesRepository
import com.blogapi.api.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentariesService {

    @Autowired
    lateinit var repository: CommentariesRepository

    @Autowired
    lateinit var repositoryUser: UserRepository


    fun save(message: Commentaries): Commentaries {
        return this.repository.save(message)
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
}