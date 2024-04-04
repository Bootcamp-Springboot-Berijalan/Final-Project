package com.example.ProjectSpringboot.repository

import com.example.ProjectSpringboot.domain.entity.EBookEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface EBookRepository: JpaRepository<EBookEntity, Int> {
    fun findByName(name: String): EBookEntity?
    fun findByNameContaining(name: String): EBookEntity
    @Query("SELECT * FROM mst_ebook WHERE (name_ebook IS NULL OR name_ebook LIKE '%:input%') OR (:idGenre IS NULL OR :idGenre = ANY(id_genre))", nativeQuery = true)
    fun search(input: String?, idGenre: Int?): List<EBookEntity>
}