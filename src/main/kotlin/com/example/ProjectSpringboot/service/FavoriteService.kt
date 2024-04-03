package com.example.ProjectSpringboot.service

import com.example.ProjectSpringboot.domain.dto.request.ReqFavoriteDto
import com.example.ProjectSpringboot.domain.dto.response.ResFavoriteDto
import com.example.ProjectSpringboot.domain.dto.response.ResMessageDto

interface FavoriteService {
    fun create(token: String, req: ReqFavoriteDto): ResMessageDto<String>
    fun get(token: String): ResMessageDto<List<ResFavoriteDto>>
}