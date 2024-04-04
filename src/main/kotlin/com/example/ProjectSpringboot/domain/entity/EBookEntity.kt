package com.example.ProjectSpringboot.domain.entity

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

@Entity
@Table(name = "mst_ebook")
data class EBookEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ebook")
    val id: Int? = null,
    @Column(name = "name_ebook")
    var name: String? = null,
    @Column(name = "author")
    var author: String? = null,
    @Column(name = "id_type")
    var type: String? = null,
    @Column(name = "id_genre")
    var genre: List<Int>? = null,
    @Column(name = "dt_added")
    var dtAdded: LocalDateTime? = null,
    @Column(name = "dt_updated")
    var dtUpdated: LocalDateTime? = null,
)
