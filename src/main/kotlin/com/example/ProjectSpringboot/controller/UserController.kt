package com.example.ProjectSpringboot.controller

import com.example.ProjectSpringboot.domain.dto.request.ReqUserDto
import com.example.ProjectSpringboot.domain.dto.response.ResMessageDto
import com.example.ProjectSpringboot.domain.dto.response.ResUserDto
import com.example.ProjectSpringboot.service.UserService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${rootLink.link}/user")
class UserController(
    val userService: UserService
) {
    @PostMapping("/create")
    fun createNewUser(
        @Valid
        @RequestBody req: ReqUserDto
    ): ResponseEntity<ResMessageDto<ResUserDto>> {
        return ResponseEntity.ok(userService.create(req))
    }
    @PutMapping("/update")
    fun updateUser(
        @Valid
        @RequestBody req: ReqUserDto,
        @RequestParam id: Int
    ): ResponseEntity<ResMessageDto<ResUserDto>>{
        return ResponseEntity.ok(userService.update(id, req))
    }
    @GetMapping
    fun gelAllUser(): ResponseEntity<ResMessageDto<List<ResUserDto>>>{
        return ResponseEntity.ok(userService.getAll())
    }
    @GetMapping("/")
    fun getUserById(
        @RequestParam id: Int
    ): ResponseEntity<ResMessageDto<ResUserDto>>{
        return ResponseEntity.ok(userService.getById(id))
    }
}