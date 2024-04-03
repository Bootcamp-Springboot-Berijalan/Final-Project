package com.example.ProjectSpringboot.repository

import com.example.ProjectSpringboot.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Int> {
    fun findByUsername(username: String): UserEntity?
    fun findByEmail(email: String): UserEntity?
    fun countByUsername(username: String): Int
    fun countByEmail(email: String): Int
}