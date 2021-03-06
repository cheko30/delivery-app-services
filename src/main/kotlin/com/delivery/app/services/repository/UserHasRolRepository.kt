package com.delivery.app.services.repository

import com.delivery.app.services.domain.UserHasRol
import com.delivery.app.services.domain.UserHasRolPk
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface UserHasRolRepository : CrudRepository<UserHasRol, Long> {
    fun findByIdRol(idRol: Long): List<UserHasRol>

}