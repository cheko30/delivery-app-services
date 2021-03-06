package com.delivery.app.services.service

import com.delivery.app.services.domain.User
import io.micronaut.http.multipart.StreamingFileUpload
import reactor.core.publisher.Mono

interface UserService {

    /**
     * @author SPO
     * @since 15/10/2021
     * @return A list of delivery users
     */
    fun findDealers(): List<User>

    /**
     * @author SPO
     * @since 15/10/2021
     * @return User admin
     */
    fun findAdmin(): List<String?>

    /**
     * @author SPO
     * @since 25/10/2021
     * return User saved
     */
    fun saveUser(user: User, file: StreamingFileUpload): Mono<User>

}