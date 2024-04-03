package com.example.ProjectSpringboot.domain.dto.request

import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class ReqUserDto(
    @field:Pattern(regexp = "^[a-zA-Z ]*\$", message = "name cannot contain numeric")
    val name: String,
    @field:Pattern(regexp = "^[^\\s]*\$", message = "username cannot contain spaces")
    val username: String,
    @field:Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$", message = "email must in the right email format")
    val email: String,
    @field:Size(min = 8, message = "password must be >8 char")
    val password: String,
    val type: String,
)
