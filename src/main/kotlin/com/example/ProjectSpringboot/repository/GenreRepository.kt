package com.example.ProjectSpringboot.repository

import com.example.ProjectSpringboot.domain.entity.GenreEntity
import org.springframework.data.jpa.repository.JpaRepository

interface GenreRepository: JpaRepository<GenreEntity, Int> {
    fun findByName(name: String): GenreEntity?
    fun findByNameContaining(name: String): GenreEntity
}