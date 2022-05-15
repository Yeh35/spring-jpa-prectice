package com.yeh35.datajpa.repository

import com.yeh35.datajpa.entity.Member
import javax.persistence.EntityManager




interface MemberRepositoryCustom {
    fun findMemberCustom(): List<Member>
}


class MemberRepositoryCustomImpl(private val em: EntityManager) : MemberRepositoryCustom {

    @Suppress("UNCHECKED_CAST")
    override fun findMemberCustom(): List<Member> {
        return em.createQuery("select m from Member m").resultList as List<Member>
    }
}