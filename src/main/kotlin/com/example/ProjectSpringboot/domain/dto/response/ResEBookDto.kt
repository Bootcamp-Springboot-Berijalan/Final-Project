package com.example.ProjectSpringboot.domain.dto.response

data class ResEBookDto(
    val id: Int?,
    val name: String,
    val author: String,
    val type: String,
    val genre: List<String>,
)
