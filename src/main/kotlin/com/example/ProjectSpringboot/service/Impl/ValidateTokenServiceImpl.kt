package com.example.ProjectSpringboot.service.Impl

import com.example.ProjectSpringboot.domain.dto.response.ResJwtValidationDto
import com.example.ProjectSpringboot.service.ValidateTokenService
import com.example.ProjectSpringboot.util.JwtGenerator
import org.springframework.stereotype.Service

@Service
class ValidateTokenServiceImpl(): ValidateTokenService {
    override fun validateToken(token: String): ResJwtValidationDto {
        val claim = JwtGenerator().decodeJwt(token)
        return ResJwtValidationDto(
            claim.subject,
            claim["username"].toString(),
            claim["type"].toString(),
        )
    }
}