package com.blogapi.api.models

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import org.hibernate.annotations.Type
import org.jetbrains.annotations.NotNull
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*
import javax.persistence.*



@Entity
class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    @ApiModelProperty(notes = "Provided title", required = true)
    var title = ""

    @Column
    var author = ""

    @Column
    var text = ""

    @Column
    var category = ""

    @Column
    @Type(type = "date")
    @JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    var creationDate: java.sql.Date = Date(0L)
        get() = field
        set(value) {
            val df = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
            df.timeZone = TimeZone.getDefault()
            val date = Calendar.getInstance().time
            val tml = date.time
            field = java.sql.Date(tml)
        }
}