package com.example.ProjectSpringboot.controller

import com.example.ProjectSpringboot.domain.dto.request.ReqGenreDto
import com.example.ProjectSpringboot.domain.dto.response.ResGenreDto
import com.example.ProjectSpringboot.domain.dto.response.ResMessageDto
import com.example.ProjectSpringboot.domain.dto.response.ResUserDto
import com.example.ProjectSpringboot.service.GenreService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${rootLink.link}/genre")
class GenreController(
    val genreService: GenreService
) {
    @PostMapping("/create")
    fun createNewGenre(
        @RequestBody req: ReqGenreDto
    ): ResponseEntity<ResMessageDto<ResGenreDto>>{
        val res = genreService.create(req)
        return ResponseEntity.ok(res)
    }
    @PutMapping("/update")
    fun updateGenre(
        @RequestBody req: ReqGenreDto,
        @RequestParam id: Int
    ): ResponseEntity<ResMessageDto<ResGenreDto>>{
        val res = genreService.update(id, req)
        return ResponseEntity.ok(res)
    }
    @GetMapping
    fun gelAllTypes(): ResponseEntity<ResMessageDto<List<ResGenreDto>>>{
        return ResponseEntity.ok(genreService.getAll())
    }
    @GetMapping("/")
    fun getTypeById(
        @RequestParam id: Int
    ): ResponseEntity<ResMessageDto<ResGenreDto>>{
        return ResponseEntity.ok(genreService.getById(id))
    }
}