package com.example.ProjectSpringboot.service

import com.example.ProjectSpringboot.domain.dto.request.ReqEBookDto
import com.example.ProjectSpringboot.domain.dto.response.ResEBookDto
import com.example.ProjectSpringboot.domain.dto.response.ResMessageDto

interface EBookService {
    fun create(req: ReqEBookDto): ResMessageDto<ResEBookDto>
    fun update(id: Int, req: ReqEBookDto): ResMessageDto<ResEBookDto>
    fun getAll(token: String): ResMessageDto<List<ResEBookDto>>
    fun searchBook(input: String): ResMessageDto<List<ResEBookDto>>
}