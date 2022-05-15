package com.yeh35.datajpa.repository

import com.yeh35.datajpa.entity.Member
import org.springframework.beans.factory.annotation.Autowired
import javax.persistence.EntityManager

class MemberRepositoryImpl : MemberRepositoryCustom {

    @Autowired
    private lateinit var em: EntityManager

    @Suppress("UNCHECKED_CAST")
    override fun findMemberCustom(): List<Member> {
        return em.createQuery("SELECT m from Member m").resultList.toList() as List<Member>
    }
}