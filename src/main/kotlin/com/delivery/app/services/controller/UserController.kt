package com.delivery.app.services.controller

import com.delivery.app.services.model.ResponseDTO
import com.delivery.app.services.domain.User
import com.delivery.app.services.repository.UserRepository
import com.delivery.app.services.service.UserService
import com.delivery.app.services.service.impl.UserServiceImpl
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.http.multipart.StreamingFileUpload
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.inject.Inject
import org.slf4j.LoggerFactory
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Controller("/api/users")
@Tag(name = "User")
class UserController {

    private val logger = LoggerFactory.getLogger(UserController::class.java)

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var userService: UserService


    @Get
    @Operation(summary = "Get all user", description = "Return a list of user")
    @ApiResponses(
        ApiResponse(
            responseCode = "200", description = "OK",
            content = [Content(schema = Schema(name = "Response", implementation = ResponseDTO::class))]
        ),
    )
    fun getAllUsers(): HttpResponse<*> {
        val userList: List<User> = userRepository.findAll()
        val responseDTO: ResponseDTO
        if (userList.isEmpty()) {
            responseDTO = ResponseDTO(
                status = HttpStatus.NOT_FOUND.code,
                statusDescription = HttpStatus.NOT_FOUND,
                messageError = "No users",
                path = "/api/users",
            )
            return HttpResponse.notFound(responseDTO)
        }
        responseDTO = ResponseDTO(
            status = HttpStatus.OK.code,
            statusDescription = HttpStatus.OK,
            messageOK = "Successful",
            path = "/api/users",
            data = userList
        )
        return HttpResponse.ok(responseDTO)
    }

    @Get("{id}")
    @Operation(summary = "Get user by id", description = "Return a user")
    fun getUserById(@PathVariable id:Long): HttpResponse<*> {
        val responseDTO: ResponseDTO
        val user = userRepository.findById(id)
        if (user.isEmpty) {
            responseDTO = ResponseDTO(
                status = HttpStatus.NOT_FOUND.code,
                statusDescription =  HttpStatus.NOT_FOUND,
                messageError = "User not found",
                path = "/api/user/${id}"
            )
            return HttpResponse.notFound(responseDTO)
        }

        responseDTO = ResponseDTO(
            status = HttpStatus.OK.code,
            statusDescription = HttpStatus.OK,
            messageOK = "Successful",
            path = "/api/user/${id}",
            data = user.get()
        )

        return HttpResponse.ok(responseDTO)
    }

    @Get("/dealers")
    @Operation(summary = "Get user with rol delivery", description = "Return a user with rol delivery")
    fun getUserByDeliveryMan(): HttpResponse<*> {
        return HttpResponse.ok(userService.findDealers())
    }

    @Get("/adminNotificationTokens")
    @Operation(summary = "Get token admin", description = "Get token of user with rol restaurant")
    fun getRestaurantToken(): HttpResponse<*> {
        return  HttpResponse.ok(userService.findAdmin())
    }

    @Post(consumes = [MediaType.MULTIPART_FORM_DATA], produces = [MediaType.APPLICATION_JSON])
    @Operation(summary = "Create a user", description = "Create a user with images")
    fun createUser(@Body user: User, file: StreamingFileUpload): Mono<HttpResponse<Any>>? {
        user.createdAt = LocalDateTime.now()
        user.updatedAt = LocalDateTime.now()
        var result = userService.saveUser(user, file)
        return Mono.from(result)
            .map { u ->
                if (u.id > 0) {
                    HttpResponse.ok(u)
                } else {
                    HttpResponse.serverError()
                }
            }
    }


    @Get("test")
    @Operation(summary = "Test service")
    fun test(): HttpResponse<*> {
        val response = mapOf("status" to true, "message" to "Success")
        return HttpResponse.ok(response)
    }

}