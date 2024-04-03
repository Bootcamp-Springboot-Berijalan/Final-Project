package com.example.ProjectSpringboot.service

import com.example.ProjectSpringboot.domain.dto.request.ReqLoginDto
import com.example.ProjectSpringboot.domain.dto.response.ResLoginDto

interface LoginService {
    fun login(req: ReqLoginDto): ResLoginDto
}