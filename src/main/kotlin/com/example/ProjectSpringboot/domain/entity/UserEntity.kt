package com.example.ProjectSpringboot.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "mst_user")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    var id: Int? = null,
    @Column(name = "name")
    var name: String? = null,
    @Column(name = "user_name")
    var username: String? = null,
    @Column(name = "email")
    var email: String? = null,
    @Column(name = "password")
    var password: String? = null,
    @Column(name = "id_type")
    var type: String? = null
)
