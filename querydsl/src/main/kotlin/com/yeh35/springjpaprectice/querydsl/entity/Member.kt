package com.yeh35.springjpaprectice.querydsl.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne


@Entity
class Member @JvmOverloads constructor(private val username: String, private val age: Int = 0, team: Team? = null) {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    var id: Long? = null
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    var team: Team? = null
        private set

    init {
        if (team != null) {
            changeTeam(team)
        }
    }

    fun changeTeam(team: Team) {
        this.team = team
        val memberList = team.members.toMutableList()
        memberList.add(this)
        team.members = memberList.toList()
    }
}