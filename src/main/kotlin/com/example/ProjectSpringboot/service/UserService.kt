package com.example.ProjectSpringboot.service

import com.example.ProjectSpringboot.domain.dto.request.ReqUserDto
import com.example.ProjectSpringboot.domain.dto.response.ResMessageDto
import com.example.ProjectSpringboot.domain.dto.response.ResUserDto

interface UserService {
    fun create(req: ReqUserDto): ResMessageDto<ResUserDto>
    fun update(id: Int, req: ReqUserDto): ResMessageDto<ResUserDto>
    fun getAll(): ResMessageDto<List<ResUserDto>>
    fun getById(id: Int): ResMessageDto<ResUserDto>
//    fun delete(id: Int): ResMessageDto<String>
}