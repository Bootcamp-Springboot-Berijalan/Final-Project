package com.example.ProjectSpringboot.service

import com.example.ProjectSpringboot.domain.dto.request.ReqGenreDto
import com.example.ProjectSpringboot.domain.dto.response.ResGenreDto
import com.example.ProjectSpringboot.domain.dto.response.ResMessageDto

interface GenreService {
    fun create(req: ReqGenreDto): ResMessageDto<ResGenreDto>
    fun update(id: Int, req: ReqGenreDto): ResMessageDto<ResGenreDto>
    fun getAll(): ResMessageDto<List<ResGenreDto>>
    fun getById(id: Int): ResMessageDto<ResGenreDto>
}