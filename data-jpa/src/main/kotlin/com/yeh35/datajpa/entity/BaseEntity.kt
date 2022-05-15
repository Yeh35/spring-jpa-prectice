package com.yeh35.datajpa.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@EntityListeners(value = [AuditingEntityListener::class])
@MappedSuperclass
abstract class BaseTimeEntity {
    @CreatedDate
    @Column(updatable = false)
    var createdDate: LocalDateTime
        private set

    @LastModifiedDate
    var lastModifiedDate: LocalDateTime
        private set

    init {
        val now = LocalDateTime.now()
        createdDate = now
        lastModifiedDate = now
    }
}

@EntityListeners(value = [AuditingEntityListener::class])
@MappedSuperclass
abstract class BaseEntity : BaseTimeEntity() {
    @CreatedBy
    @Column(updatable = false)
    var createdBy: String
        private set

    @LastModifiedBy
    var lastModifiedBy: String
        private set

    init {
        createdBy = ""
        lastModifiedBy = ""
    }
}
