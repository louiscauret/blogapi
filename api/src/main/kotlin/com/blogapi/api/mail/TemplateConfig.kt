package com.blogapi.api.mail

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.SimpleMailMessage

@Configuration
class TemplateConfig {
    @Bean
    fun resetPasswordTemplate(): SimpleMailMessage {
        val template = SimpleMailMessage()
        template.setSubject("Reset Password")
        template.setText("""
            Hello %s, 
            
            This is an email message to reset your password
        """.trimIndent()
        )
        return template
    }
}