package com.github.myugen.kotlinspringboilerplate.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
class AuditingConfiguration {
    @Bean
    fun auditorProvider(): AuditorAware<String> {
        if (SecurityContextHolder.getContext().authentication != null) {
            val principal = SecurityContextHolder.getContext().authentication as UsernamePasswordAuthenticationToken
            return AuditorAware { Optional.of(principal.name) }
        }
        return AuditorAware { Optional.of("unknown") }
    }
}