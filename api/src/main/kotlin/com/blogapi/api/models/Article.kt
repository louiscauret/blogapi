package com.blogapi.api.models

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import org.hibernate.annotations.Type
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*
import javax.persistence.*

@Entity
class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var idArticle: Int = 0

    @Column
    @ApiModelProperty(notes = "Provided title", required = true)
    var title = ""

    @ManyToOne
    @JoinColumn(name="idUser", nullable=false)
    var author: User = User()

    @Column
    var text = ""

    @Column
    var category = ""

    @Column
    @Type(type = "date")
    @JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    var creationDate: Date = Date(0L)
        get() = field
        set(value) {
            val df = SimpleDateFormat("dd-MM-yyyy")
            df.timeZone = TimeZone.getDefault()
            val date = Calendar.getInstance().time
            val tml = date.time
            field = Date(tml)
        }
}