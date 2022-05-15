package com.yeh35.datajpa.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Team {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    var id: Long = 0
        private set

    var name: String
        private set

    @OneToMany(mappedBy = "team")
    var members: List<Member> = listOf()
        private set

    constructor(name: String) {
        this.name = name
    }

    fun changeMembers(members: List<Member>) {
        members.forEach { member ->
            assert(member.team == this)
        }
        this.members = members
    }
}