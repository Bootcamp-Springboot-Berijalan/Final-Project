package com.example.ProjectSpringboot.service

import com.example.ProjectSpringboot.domain.dto.request.ReqTypeDto
import com.example.ProjectSpringboot.domain.dto.response.ResMessageDto
import com.example.ProjectSpringboot.domain.dto.response.ResTypeDto

interface TypeService {
//    fun create(req: ReqTypeDto): ResMessageDto<ResTypeDto>
    fun addRow(): ResMessageDto<List<ResTypeDto>>
    fun getAll(): ResMessageDto<List<ResTypeDto>>
    fun getById(id: String): ResMessageDto<ResTypeDto>
}