package com.delivery.app.services.repository

import com.delivery.app.services.domain.User
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface UserRepository : CrudRepository<User, Long> {
    override fun findAll(): List<User>
}