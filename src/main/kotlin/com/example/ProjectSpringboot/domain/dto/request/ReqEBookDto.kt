package com.example.ProjectSpringboot.domain.dto.request

data class ReqEBookDto(
    val name: String,
    val author: String,
    val type: String,
    val genre: List<Int>
)
