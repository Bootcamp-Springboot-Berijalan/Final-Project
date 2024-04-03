package com.example.ProjectSpringboot.repository

import com.example.ProjectSpringboot.domain.entity.FavoriteEntity
import org.springframework.data.jpa.repository.JpaRepository

interface FavoriteRepository: JpaRepository<FavoriteEntity, Int> {
    fun findAllByUserAdded(user: String): List<FavoriteEntity>
}