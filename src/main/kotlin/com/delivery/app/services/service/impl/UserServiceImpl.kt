package com.delivery.app.services.service.impl

import com.delivery.app.services.config.FirebaseService
import com.delivery.app.services.domain.User
import com.delivery.app.services.domain.UserHasRol
import com.delivery.app.services.repository.UserHasRolRepository
import com.delivery.app.services.repository.UserRepository
import com.delivery.app.services.service.UserService
import io.micronaut.http.multipart.StreamingFileUpload
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import reactor.core.publisher.Mono
import java.io.File
import java.time.LocalDate
import java.util.*

@Singleton
open class UserServiceImpl(
    private val userHasRolRepository: UserHasRolRepository,
    private val userRepository: UserRepository,
    private val storageService: FirebaseService
): UserService {

    private val logger = LoggerFactory.getLogger(UserServiceImpl::class.java)

    override fun findDealers(): List<User> {
        val deliveryRol: Long = 3
        var userHasRolList = userHasRolRepository.findByIdRol(deliveryRol)
        var userList: MutableList<User> = mutableListOf()
        userHasRolList.forEach{
            var user = userRepository.findById(it.idUser).orElse(null)
            userList.add(user)
        }

        logger.info("LISTA DE REPARTIDORES -> ${userList.size}")
        return userList
    }

    override fun findAdmin(): List<String?> {
        val restaurantRol: Long = 2
        var userHasRol = userHasRolRepository.findByIdRol(restaurantRol)
        var adminTokenList: MutableList<String?> = mutableListOf()
        userHasRol.forEach {
            userRepository.findById(it.idUser).ifPresent { u -> adminTokenList.add(u.notificationToken) }
        }
        return adminTokenList
    }

    override fun saveUser(user: User, file: StreamingFileUpload): Mono<User> {
        val tempFile = File.createTempFile(file.filename, "temp")
        val uploadPublisher = file.transferTo(tempFile)

        return Mono.from(uploadPublisher)
            .map { success ->
                if (success) {
                    var pathImage = "image_${LocalDate.now()}"
                    var result = storageService.saveFile(pathImage, tempFile)
                    if (result.exists()) {
                        user.image = result.mediaLink
                        userRepository.save(user)
                        var userHasRol: UserHasRol = UserHasRol(idUser = user.id, idRol = 1L)
                        userHasRolRepository.save(userHasRol)
                        return@map user
                    } else {
                        return@map user
                    }
                } else {
                    return@map user
                }
            }
    }
}