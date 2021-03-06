package com.delivery.app.services.model

import io.micronaut.http.HttpStatus
import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "Response", description = "Response")
class ResponseDTO
    (
    @field:Schema(description = "Number status HTTP", example = "200")
    var status:Int,
    @field:Schema(description = "Description status HTTP", example = "OK")
    var statusDescription:HttpStatus,
    @field:Schema(description = "Message error", example = "No users")
    var messageError:String? = null,
    @field:Schema(description = "Message ok", example = "Successful")
    var messageOK:String? = null,
    @field:Schema(description = "Path resource", example = "/api/users/getAll")
    var path:String? = null,
    @field:Schema(description = "Data result", example = "{}")
    var data: Any? = null,
)

