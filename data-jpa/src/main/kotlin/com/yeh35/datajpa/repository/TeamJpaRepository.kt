package com.yeh35.datajpa.repository

import com.yeh35.datajpa.entity.Team
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import java.util.Optional

@Repository
class TeamJpaRepository {

    @PersistenceContext
    private val em: EntityManager? = null

    fun save(team: Team): Team {
        em!!.persist(team)
        return team
    }

    fun delete(team: Team) {
        em!!.remove(team)
    }

    fun findAll(): List<Team> {
        return em!!.createQuery("select t from Team t", Team::class.java).resultList
    }

    fun findById(id: Long): Optional<Team> {
        val team = em!!.find(Team::class.java, id)
        return Optional.ofNullable(team)
    }

    fun count(): Long {
        val singleResult = em!!.createQuery("select count(t) from Team t").singleResult
        return singleResult.toString().toLong()
    }
}
