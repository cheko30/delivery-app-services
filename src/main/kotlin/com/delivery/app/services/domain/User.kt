package com.delivery.app.services.domain

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.Relation
import java.time.LocalDateTime
import javax.persistence.*

@Introspected
@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var email: String,
    var name: String,
    var lastname: String,
    var phone: String,
    var image: String?,
    @field:Column(name = "is_available")
    var available: Boolean?,
    var sessionToken: String?,
    var password: String,
    @Column(name = "created_at")
    var createdAt: LocalDateTime?,
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime?,
    var notificationToken: String?,

)
