package com.example.ProjectSpringboot.service

import com.example.ProjectSpringboot.domain.dto.response.ResJwtValidationDto

interface ValidateTokenService {
    fun validateToken(token: String): ResJwtValidationDto
}