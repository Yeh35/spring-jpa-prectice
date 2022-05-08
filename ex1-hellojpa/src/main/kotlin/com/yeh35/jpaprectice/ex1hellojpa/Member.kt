package com.yeh35.jpaprectice.ex1hellojpa

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "members")
class Member {

    @Id
    var id: Long = 0
        private set

    var name: String
        private set

    constructor(name: String) {
        this.name = name
    }
}