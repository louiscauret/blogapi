package com.blogapi.api.mail

class EmailRequest(
        val subject: String?,
        val targetEmail: String?,
        val text: String?,
        val name: String?
)