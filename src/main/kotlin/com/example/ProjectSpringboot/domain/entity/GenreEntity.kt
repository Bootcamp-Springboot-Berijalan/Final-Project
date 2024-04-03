package com.example.ProjectSpringboot.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "mst_genre")
data class GenreEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genre")
    val id: Int? = null,
    @Column(name = "genre_name")
    var name: String? = null
)
