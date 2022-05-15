package com.yeh35.datajpa.entity

import javax.persistence.*

@Entity
class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    var id: Long = 0
        private set

    var username: String
        private set

    var age: Int
        private set

    @JoinColumn(name = "team_id")
    @ManyToOne(fetch = FetchType.LAZY)
    var team: Team?
        private set

    constructor(username: String, age: Int, team: Team? = null) {
        this.username = username
        this.age = age
        this.team = team
    }

    fun changeUsername(username: String) {
        this.username = username
    }

    fun changeTeam(team: Team) {
        val oldList = this.team!!.members.toMutableList()
        oldList.remove(this)
        this.team!!.changeMembers(oldList)

        val newList = team.members.toMutableList()
        newList.add(this)
        team.changeMembers(newList)

        this.team = team
    }
}