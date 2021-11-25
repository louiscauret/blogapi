package com.blogapi.api.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.io.File
import java.net.URL
import java.nio.file.Paths
import javax.persistence.*

@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var idUser: Int = 0

    @Column
    var firstName: String = ""

    @Column
    var lastName: String = ""

    @Column(unique = true)
    var email: String = ""

    @Column
    var password: String = ""
        @JsonIgnore
        get() = field
        set(value) {
            val passwordEncoder = BCryptPasswordEncoder()
            field = passwordEncoder.encode(value)
        }

    fun comparePassword(password: String): Boolean{
        return BCryptPasswordEncoder().matches(password, this.password)
    }

    @Column(nullable = true)
    var avatar = ""
        get() = field
        set(value) {
            val host = "https://eu.ui-avatars.com/api/"

            val url = URL("$host?name=$value")

            val dir = Paths.get(System.getProperty("user.dir"))
            val fileName = "${System.currentTimeMillis()}-$value-image.png"
            val file = File("$dir/images/$fileName")
            file.createNewFile()

            val connection = url.openConnection()
            connection.doInput = true
            connection.connect()

            val content = url.readBytes()

            file.writeBytes(content)
            field = file.absolutePath
        }

    @OneToMany(targetEntity = Article::class, mappedBy = "author")
    private val articles: List<Article> = ArrayList()
}