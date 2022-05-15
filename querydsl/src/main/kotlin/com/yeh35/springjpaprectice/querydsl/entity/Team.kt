package com.yeh35.springjpaprectice.querydsl.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Team(private val name: String) {
    @Id @GeneratedValue
    @Column(name = "team_id")
    var id: Long? = null
        private set

    @OneToMany(mappedBy = "team")
    var members: List<Member> = ArrayList()
        internal set

}