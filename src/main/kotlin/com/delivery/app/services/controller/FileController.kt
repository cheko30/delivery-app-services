package com.delivery.app.services.controller

import com.delivery.app.services.config.FirebaseService
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.multipart.StreamingFileUpload
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.inject.Inject
import org.slf4j.LoggerFactory
import reactor.core.publisher.Mono
import java.io.File

@Controller("/api/files")
@Tag(name = "Files")
class FileController {

    private val log = LoggerFactory.getLogger(FileController::class.java)

   @Inject
   lateinit var storageService: FirebaseService

   @Post("/upload", consumes = [MediaType.MULTIPART_FORM_DATA], produces = [MediaType.TEXT_PLAIN])
   @Operation(summary = "Save file in Google Cloud Storage", description = "The file will save in a bucket google storage")
   fun upload(file: StreamingFileUpload): Mono<HttpResponse<*>> {
       val temFile = File.createTempFile(file.filename, "temp")
       val uploadPublisher = file.transferTo(temFile)

      return Mono.from(uploadPublisher)
           .map { success ->
               if (success) {
                   var fileUpload = storageService.saveFile(file.filename, temFile)
                   log.info("FILE -> $fileUpload")
                   HttpResponse.ok("File Uploaded")
               } else {
                   HttpResponse.status<String>(HttpStatus.CONFLICT).body("File Upload Failed")
               }

           }

   }

}