package com.yeh35.datajpa.repository

import com.yeh35.datajpa.dto.MemberDto
import com.yeh35.datajpa.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface MemberRepository : JpaRepository<Member, Long> {

    fun findByUsernameAndAgeGreaterThan(username: String, age: Int): List<Member>

    @Query("select m from Member m where m.username= :username and m.age = :age")
    fun findUser(@Param("username") username: String?, @Param("age") age: Int): List<Member?>?

    @Query("SELECT m.username from Member m")
    fun findUsernameList(): List<String>

//    @Query("SELECT m.id, m.username, m.team.name from Member m join m.team")
    @Query("SELECT new com.yeh35.datajpa.dto.MemberDto(m.id, m.username, m.team.name) from Member m join m.team")
    fun findMemberDto(): List<MemberDto>
}