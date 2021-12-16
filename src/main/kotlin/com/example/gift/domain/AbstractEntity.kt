package com.example.gift.domain

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class AbstractEntity {
    @CreationTimestamp
    @Column(name = "created_at")
    lateinit var createdAt: ZonedDateTime

    @UpdateTimestamp
    @Column(name = "updated_at")
    lateinit var updatedAt: ZonedDateTime

}