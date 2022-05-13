package com.yeh35.datajpa.entity

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Transactional
@SpringBootTest
internal class MemberTest {

    @PersistenceContext
    private lateinit var em: EntityManager

    @Test
    fun testEntity() {
        val teamA = Team("Team A")
        em.persist(teamA)

        val teamB = Team("Team B")
        em.persist(teamB)

        val member1 = Member("member1", 10, teamA)
        val member2 = Member("member2", 15, teamB)
        val member3 = Member("member3", 15, teamA)
        val member4 = Member("member4", 15, teamB)

        em.persist(member1)
        em.persist(member2)
        em.persist(member3)
        em.persist(member4)

        //초기화
        em.flush()
        em.close()

        //확인
        val members = em.createQuery("SELECT m FROM Member m", Member::class.java).resultList

        members.forEach { m ->
            println("member = $m")
            println("=> member.team = ${m.team}")
        }

    }

}