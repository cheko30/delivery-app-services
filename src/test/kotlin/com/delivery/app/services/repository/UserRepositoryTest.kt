package com.delivery.app.services.repository

import com.delivery.app.services.domain.User
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainNoNulls
import io.micronaut.test.extensions.kotest.annotation.MicronautTest

@MicronautTest(environments = ["test"])
class UserRepositoryTest(private val userRepository: UserRepository): BehaviorSpec({
    given("User repository") {
        `when`("Get all user") {
            val listUser: List<User> = userRepository.findAll()
            then("The listUser is not empty") {
                listUser.shouldContainNoNulls()
            }
        }
    }
})