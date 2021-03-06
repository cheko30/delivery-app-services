package com.delivery.app.services.domain

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user_has_roles")
data class UserHasRol(

    @Id
    var idUser: Long,
    @Id
    var idRol: Long,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now(),
) : Serializable