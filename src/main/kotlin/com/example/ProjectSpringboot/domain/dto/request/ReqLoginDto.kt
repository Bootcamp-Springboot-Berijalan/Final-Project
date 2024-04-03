package com.example.ProjectSpringboot.domain.dto.request

import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class ReqLoginDto(
    @field:Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$", message = "email must in the right email format")
    val email: String,
    @field:Size(min = 8, message = "password must be >8 char")
    val password: String,
)
