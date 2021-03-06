package com.delivery.app.services

import io.micronaut.runtime.Micronaut.*
import io.swagger.v3.oas.annotations.*
import io.swagger.v3.oas.annotations.info.*

@OpenAPIDefinition(
    info = Info(
            title = "Delivery App Services",
            version = "1.0.0",
            description = "API for app delivery",
            contact = Contact(name = "Sergio Peña Orozco", email = "sergio.pena.orozco@gmail.com")
    )
)
object Api {
}
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("com.delivery.app.services")
		.start()
}

