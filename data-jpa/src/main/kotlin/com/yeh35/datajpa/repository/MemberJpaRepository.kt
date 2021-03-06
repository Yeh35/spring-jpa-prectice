package com.yeh35.datajpa.repository

import com.yeh35.datajpa.entity.Member
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Suppress("UNCHECKED_CAST")
@Repository
class MemberJpaRepository {
    @PersistenceContext
    private val em: EntityManager? = null

    fun save(member: Member): Member {
        em!!.persist(member)
        return member
    }

    fun delete(member: Member?) {
        em!!.remove(member)
    }

    fun findAll(): List<Member> {
        return em!!.createQuery("select m from Member m", Member::class.java).resultList
    }

    fun findById(id: Long?): Optional<Member> {
        val member = em!!.find(Member::class.java, id)
        return Optional.ofNullable(member)
    }

    fun count(): Long {
        val singleResult = em!!.createQuery("select count(m) from Member m").singleResult
        return singleResult.toString().toLong()
    }

    fun find(id: Long?): Member {
        return em!!.find(Member::class.java, id)
    }

    fun findByPage(age: Int, offset: Int, limit: Int): List<Member> {
        val resultList = em!!.createQuery("SELECT m FROM Member m where m.age = :age order by m.username desc")
            .setParameter("age", age)
            .setFirstResult(offset)
            .setMaxResults(limit)
            .resultList as MutableList<Member>
        return resultList.toList()
    }
}
