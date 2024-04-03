package com.example.ProjectSpringboot.repository

import com.example.ProjectSpringboot.domain.entity.TypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TypeRepository: JpaRepository<TypeEntity, Int> {
    fun findByTypeId(type: String):TypeEntity?
    fun findByDescription(description: String): TypeEntity?
}