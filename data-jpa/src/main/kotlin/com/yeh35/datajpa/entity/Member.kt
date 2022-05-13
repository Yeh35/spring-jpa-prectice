package com.yeh35.datajpa.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Member(username: String) {

    @Id
    @GeneratedValue
    var id: Long = 0
        private set

    var username: String = username
        private set

    fun changeUsername(username: String) {
        this.username = username
    }
}