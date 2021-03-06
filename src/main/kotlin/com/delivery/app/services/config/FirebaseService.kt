package com.delivery.app.services.config

import com.google.api.gax.paging.Page
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.storage.*
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.StorageClient
import io.micronaut.context.annotation.Value
import jakarta.inject.Singleton
import org.apache.http.entity.ContentType
import org.slf4j.LoggerFactory
import java.io.File
import java.util.*
import javax.annotation.PostConstruct

@Singleton
class FirebaseService() {

    private val log = LoggerFactory.getLogger(FirebaseService :: class.java)

    lateinit var options: FirebaseOptions
    lateinit var storage: Storage

    @Value("\${gcp.bucket}")
    lateinit var bucket: String

    @Value("\${gcp.directory}")
    lateinit var directory: String

    lateinit var uuid: UUID

    @PostConstruct
    private fun initializeFirebase() {
        options = FirebaseOptions
            .builder()
            .setCredentials(GoogleCredentials.getApplicationDefault())
            .setStorageBucket(bucket)
            .build()

        FirebaseApp.initializeApp(options)
    }

    fun getFilesOfBucket(): List<String> {
        var page: Page<Blob> = storage.list(bucket, Storage.BlobListOption.currentDirectory())
        return page.values.map { blob -> blob.name  }
    }


    fun saveFile(fileName: String, tempFile: File) : Blob {
        var bucketName: Bucket = StorageClient.getInstance().bucket()
        return bucketName.create("$fileName", tempFile.readBytes(), "image/png")
    }
}