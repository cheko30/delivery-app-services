package com.delivery.app.services.domain

import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable

data class UserHasRolPk(
    val idUser: Long,
    val idRol: Long
) : Serializable
