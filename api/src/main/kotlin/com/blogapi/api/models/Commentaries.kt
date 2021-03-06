package com.blogapi.api.models

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.Type
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*
import javax.persistence.*

@Entity
class Commentaries {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var idMessage: Int = 0

    @Column
    var text = ""

    @Column
    @Type(type = "date")
    @JsonFormat
        (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    var creationDate: Date = Date(0L)
        get() = field
        set(value) {
            val df = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
            df.timeZone = TimeZone.getDefault()
            val date = Calendar.getInstance().time
            val tml = date.time
            field = Date(tml)
        }

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JsonIgnoreProperties("commentary")
    @JoinColumn(name="idArticle", nullable=false)
    var article: Articles? = Articles()

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JsonProperty("authorCommentary")
    @JsonIgnoreProperties("messages")
    @JoinColumn(name="idUser", nullable=false)
    var author: Users? = Users()
}