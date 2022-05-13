package com.yeh35.datajpa.repository

import com.yeh35.datajpa.entity.Member
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class MemberRepositoryTest {

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Test
    fun save() {
        val member = Member("김삿갓")
        val saveMember = memberRepository.save(member)

        val findMember = memberRepository.findById(saveMember.id).get()

        assertThat(findMember.id).isEqualTo(member.id)
        assertThat(findMember.username).isEqualTo(member.username)

        assertThat(findMember).isEqualTo(saveMember)

    }
}