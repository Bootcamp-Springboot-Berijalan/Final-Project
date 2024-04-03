package com.example.ProjectSpringboot.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "mst_favorite")
data class FavoriteEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_favorite")
    val id: Int? = null,
    @Column(name = "id_ebook")
    var bookId: Int? = null,
    @Column(name = "user_added")
    var userAdded: String? = null,
    @Column(name = "dt_added")
    var dtAdded: LocalDateTime? = null,
    @Column(name = "dt_updated")
    var dtUpdated: LocalDateTime? = null
)
