package com.example.ProjectSpringboot.repository

import com.example.ProjectSpringboot.domain.entity.EBookEntity
import org.springframework.data.jpa.repository.JpaRepository

interface EBookRepository: JpaRepository<EBookEntity, Int> {
    fun findByName(name: String): EBookEntity?
    fun findByNameContaining(name: String): EBookEntity
}