package com.yeh35.datajpa.repository

import com.yeh35.datajpa.entity.Member
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class MemberJpaRepositoryTest {

    @Autowired
    private lateinit var memberJpaRepository: MemberJpaRepository

    @Test
    fun save() {
        val member = Member("김삿갓")
        val saveMember = memberJpaRepository.save(member)

        val findMember = memberJpaRepository.find(saveMember.id)!!

        assertThat(findMember.id).isEqualTo(member.id)
        assertThat(findMember.username).isEqualTo(member.username)

        assertThat(findMember).isEqualTo(member)

    }
}