package com.example.ProjectSpringboot.controller

import com.example.ProjectSpringboot.domain.dto.response.ResMessageDto
import com.example.ProjectSpringboot.domain.dto.response.ResTypeDto
import com.example.ProjectSpringboot.domain.dto.response.ResUserDto
import com.example.ProjectSpringboot.service.TypeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${rootLink.link}/type")
class TypeController(
    val typeService: TypeService
) {
    @PostMapping("/create")
    fun createTypes(): ResponseEntity<ResMessageDto<List<ResTypeDto>>>{
        val res = typeService.addRow()
        return ResponseEntity.ok(res)
    }
    @GetMapping
    fun gelAllUser(): ResponseEntity<ResMessageDto<List<ResTypeDto>>>{
        return ResponseEntity.ok(typeService.getAll())
    }
    @GetMapping("/")
    fun getUserById(
        @RequestParam id: String
    ): ResponseEntity<ResMessageDto<ResTypeDto>>{
        return ResponseEntity.ok(typeService.getById(id))
    }
}