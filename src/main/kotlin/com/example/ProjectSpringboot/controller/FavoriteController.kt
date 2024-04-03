package com.example.ProjectSpringboot.controller

import com.example.ProjectSpringboot.domain.dto.request.ReqFavoriteDto
import com.example.ProjectSpringboot.domain.dto.response.ResFavoriteDto
import com.example.ProjectSpringboot.domain.dto.response.ResMessageDto
import com.example.ProjectSpringboot.service.FavoriteService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${rootLink.link}/favorite")
class FavoriteController(
    val favoriteService: FavoriteService
) {
    @PostMapping("/create")
    fun createFav(
        @RequestBody req: ReqFavoriteDto,
        @RequestHeader token: String
    ): ResponseEntity<ResMessageDto<String>>{
        return ResponseEntity.ok(favoriteService.create(token, req))
    }
    @GetMapping("/get")
    fun getFav(
        @RequestHeader token: String
    ): ResponseEntity<ResMessageDto<List<ResFavoriteDto>>>{
        return ResponseEntity.ok(favoriteService.get(token))
    }
}