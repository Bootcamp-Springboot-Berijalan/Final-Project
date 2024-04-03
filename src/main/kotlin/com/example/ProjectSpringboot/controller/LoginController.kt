package com.example.ProjectSpringboot.controller

import com.example.ProjectSpringboot.domain.dto.request.ReqLoginDto
import com.example.ProjectSpringboot.domain.dto.response.ResJwtValidationDto
import com.example.ProjectSpringboot.domain.dto.response.ResLoginDto
import com.example.ProjectSpringboot.domain.dto.response.ResMessageDto
import com.example.ProjectSpringboot.service.LoginService
import com.example.ProjectSpringboot.service.ValidateTokenService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${rootLink.link}/user")
class LoginController(
    val loginService: LoginService,
     val validateTokenService: ValidateTokenService
) {
    @PostMapping("/login")
    fun login(@RequestBody req: ReqLoginDto): ResponseEntity<ResMessageDto<ResLoginDto>> {
        val res = loginService.login(req)
        return ResponseEntity.ok(ResMessageDto(
            message = "Success Login",
            data = res
        ))
    }
    @GetMapping("/validate")
    fun validate(@RequestHeader token: String): ResponseEntity<ResMessageDto<ResJwtValidationDto>>{
        val res = validateTokenService.validateToken(token)
        return ResponseEntity.ok(ResMessageDto(
            message = "Success Decode Jwt",
            data = res
        ))
    }
}