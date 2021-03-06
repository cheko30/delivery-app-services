package com.delivery.app.services.impl

import com.delivery.app.services.config.FirebaseService
import io.kotest.core.spec.style.BehaviorSpec
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import java.io.File

@MicronautTest
class StorageServiceTest(private val storageService: FirebaseService) : BehaviorSpec({
    given("Storage Service") {
        `when`("the list blobs in storage"){
            val result = storageService.getFilesOfBucket()
            then("the result is not empty") {
                result.isNotEmpty()
            }
        }

        `when`("Save file in the sotrage") {
            val file = File("test.png")
            val result = storageService.saveFile("test.png", file)
            then("the result is not false") {
                result
            }
        }
    }
})