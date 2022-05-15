package com.yeh35.datajpa.repository

import com.yeh35.datajpa.entity.Member
import com.yeh35.datajpa.entity.Team
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.transaction.Transactional


@Transactional
@SpringBootTest
internal class MemberJpaRepositoryTest {

    @Autowired
    private lateinit var memberJpaRepository: MemberJpaRepository

    @Autowired
    private lateinit var teamJpaRepository: TeamJpaRepository

    @Test
    fun save() {
        val team = Team("TeamA")
        val member = Member("김삿갓", 11, team)
        val saveMember = memberJpaRepository.save(member)

        val findMember = memberJpaRepository.find(saveMember.id)!!

        assertThat(findMember.id).isEqualTo(member.id)
        assertThat(findMember.username).isEqualTo(member.username)

        assertThat(findMember).isEqualTo(member)
    }

    @Test
    fun basicCRUD() {
        val team = Team(name = "team")
        val m1 = Member(username = "member1", 10, team)
        val m2 = Member(username = "member2", 15, team)

        teamJpaRepository.save(team)
        memberJpaRepository.save(m1)
        memberJpaRepository.save(m2)

        val findM1 = memberJpaRepository.findById(m1.id).get()
        val findM2 = memberJpaRepository.findById(m2.id).get()

        assertThat(findM1).isEqualTo(findM1)
        assertThat(findM2).isEqualTo(findM2)

        //list 조회 검증
        val findAll = memberJpaRepository.findAll()
        assertThat(findAll.size).isEqualTo(2)

        val count = memberJpaRepository.count()
        assertThat(count).isEqualTo(2)

        memberJpaRepository.delete(m1)
        memberJpaRepository.delete(m2)

        val deleteCount = memberJpaRepository.count()
        assertThat(deleteCount).isEqualTo(0)
    }
}