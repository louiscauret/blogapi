package com.blogapi.api.models

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonManagedReference
import io.swagger.annotations.ApiModelProperty
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.io.File
import java.net.URL
import java.nio.file.Paths
import javax.persistence.*

@Entity
class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var idUser: Int = 0

    @Column
    @ApiModelProperty(notes = "First name of user", required = true)
    var firstName: String = ""

    @Column
    @ApiModelProperty(notes = "Last name of user", required = true)
    var lastName: String = ""

    @Column(unique = true)
    @ApiModelProperty(notes = "Email of user", required = true)
    var email: String = ""

    @Column
    @ApiModelProperty(notes = "Password of user encrypted", required = true)
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
    @ApiModelProperty(notes = "Avatar of user auto generate with first name and last name", required = true)
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

    @OneToMany(targetEntity = Articles::class, mappedBy = "author", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
//    @JsonManagedReference
    @JsonIgnoreProperties("author")
    private val articles: List<Articles> = ArrayList()

    @OneToMany(targetEntity = Commentaries::class, mappedBy = "author", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
//    @JsonManagedReference(value = "author")
    @JsonIgnoreProperties("author")
    private val messages: List<Commentaries> = ArrayList()
}