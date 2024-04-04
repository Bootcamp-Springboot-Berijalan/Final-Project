package com.example.ProjectSpringboot.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class PathMatchingInterceptor(
    private val authInterceptor: AuthInterceptor
): WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authInterceptor).excludePathPatterns(
            "/v1/api/final-project/user/login", "/v1/api/final-project/user/validate",
            "/v1/api/final-project/user/create"
        )
    }
}