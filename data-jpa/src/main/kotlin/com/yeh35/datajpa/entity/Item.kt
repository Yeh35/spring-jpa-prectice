package com.yeh35.datajpa.entity

import org.springframework.data.annotation.CreatedDate

import org.springframework.data.domain.Persistable
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id


@Entity
class Item : Persistable<String> {

    @Id
    private var id: String

    @CreatedDate
    private val createdDate: LocalDateTime? = null

    constructor(id: String) {
        this.id = id
    }

    override fun getId(): String {
        return id
    }

    override fun isNew(): Boolean {
        return createdDate == null
    }
}