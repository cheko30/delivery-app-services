package com.delivery.app.services.config

import com.google.api.gax.paging.Page
import com.google.cloud.storage.*
import io.micronaut.context.annotation.Value
//import io.micronaut.gcp.GoogleCloudConfiguration
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.io.File
import javax.annotation.PostConstruct

//@Singleton
class StorageService() {
/*
    lateinit var storage: Storage

    @Inject
    lateinit var googleCloudConfiguration: GoogleCloudConfiguration

    @Value("\${gcp.bucket}")
    lateinit var bucket: String

    @PostConstruct
    fun initialize() {
        val projectId:String = googleCloudConfiguration.projectId
        storage = StorageOptions.newBuilder().setProjectId(projectId).build().service
    }

    fun getFilesOfBucket(): List<String> {
        var page: Page<Blob> = storage.list(bucket, Storage.BlobListOption.currentDirectory())
        return page.values.map { blob -> blob.name  }
    }

    fun saveFile(filename:String, tempFile: File): Blob {
        val name: String = filename
        val blobId: BlobId = BlobId.of(bucket, name)
        val blobInfo: BlobInfo = BlobInfo.newBuilder(blobId).build()
        return storage.create(blobInfo, tempFile.readBytes())
    }

*/
}