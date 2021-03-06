package com.delivery.app.services.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "roles")
data class Rol(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var name: String,
    var image: String,
    var route: String,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now(),
)
