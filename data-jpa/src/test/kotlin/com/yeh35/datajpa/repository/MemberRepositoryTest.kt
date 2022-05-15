package com.yeh35.datajpa.repository

import com.yeh35.datajpa.entity.Member
import com.yeh35.datajpa.entity.Team
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class MemberRepositoryTest {

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var teamRepository: TeamRepository

    @Test
    fun classCheck() {
        println("memberRepository: ${memberRepository::class.java}")
        println("teamRepository: ${teamRepository::class.java}")
    }

    @Test
    fun save() {
        val team = Team("Team")
        val member = Member("김삿갓", 15, team)
        val saveMember = memberRepository.save(member)

        val findMember = memberRepository.findById(saveMember.id).get()

        assertThat(findMember.id).isEqualTo(member.id)
        assertThat(findMember.username).isEqualTo(member.username)

        assertThat(findMember).isEqualTo(saveMember)
    }

    @Test
    fun findByUsernameAndAgeGreaterThan() {
        val team = Team("Team")
        val username = "김삿갓"
        val member = Member(username, 15, team)
        teamRepository.save(team)
        memberRepository.save(member)

        val memberList = memberRepository.findByUsernameAndAgeGreaterThan(username, 10)
        memberList.forEach {m ->
            println("member: ${m.username}, ${m.age}")
        }

    }

    @Test
    fun findMemberDto() {
        val team = Team("Team")
        teamRepository.save(team)

        val username = "김삿갓"
        val member = Member(username, 15, team)
        memberRepository.save(member)

        val memberDtoList = memberRepository.findMemberDto()
        memberDtoList.forEach {m ->
            println("member: ${m.id}, ${m.username}, ${m.teamName}")
        }

    }
}