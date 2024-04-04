package com.example.ProjectSpringboot.controller

import com.example.ProjectSpringboot.domain.dto.request.ReqEBookDto
import com.example.ProjectSpringboot.domain.dto.response.ResEBookDto
import com.example.ProjectSpringboot.domain.dto.response.ResMessageDto
import com.example.ProjectSpringboot.service.EBookService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlin.contracts.Returns

@RestController
@RequestMapping("\${rootLink.link}/ebook")
class EBookController(
    val eBookService: EBookService
) {
    @PostMapping("/create")
    fun createEbook(
        @RequestBody req: ReqEBookDto
    ): ResponseEntity<ResMessageDto<ResEBookDto>>{
        val res = eBookService.create(req)
        return ResponseEntity.ok(res)
    }
    @PutMapping("/update")
    fun updateEbook(
        @RequestParam id: Int,
        @RequestBody req: ReqEBookDto
    ): ResponseEntity<ResMessageDto<ResEBookDto>>{
        val res = eBookService.update(id, req)
        return ResponseEntity.ok(res)
    }
    @GetMapping("/")
    fun getAllBook(
        @RequestHeader token: String
    ): ResponseEntity<ResMessageDto<List<ResEBookDto>>>{
        return ResponseEntity.ok(eBookService.getAll(token))
    }
    @GetMapping("/search")
    fun searchBook(
        @RequestParam input: String
    ): ResponseEntity<ResMessageDto<List<ResEBookDto>>>{
        return ResponseEntity.ok(eBookService.searchBook(input))
    }
}