package com.blogapi.api.controllers

import com.blogapi.api.mail.EmailRequest
import com.blogapi.api.services.EmailSenderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class EmailController (private val emailSenderService: EmailSenderService) {

    @PostMapping("reset-password")
    fun sendSimpleEmail (@RequestBody request: EmailRequest): ResponseEntity<Any> {
        emailSenderService.sendEmail(
                subject = request.subject!!,
                targetEmail = request.targetEmail!!,
                text = request.text!!
        )
        return ResponseEntity.noContent().build()
    }

    @PostMapping("reset-password/template")
    fun sendSimpleTemplateEmail (@RequestBody request: EmailRequest): ResponseEntity<Any> {
        emailSenderService.sendEmailUsingTemplate(
                name = request.name!!,
                targetEmail = request.targetEmail!!
        )
        return ResponseEntity.noContent().build()
    }

}