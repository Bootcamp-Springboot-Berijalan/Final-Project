package com.example.ProjectSpringboot.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "mst_type")
data class TypeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Int? = null,
    @Column(name = "id_type")
    var typeId: String? = null,
    @Column(name = "description")
    var description: String? = null
)
