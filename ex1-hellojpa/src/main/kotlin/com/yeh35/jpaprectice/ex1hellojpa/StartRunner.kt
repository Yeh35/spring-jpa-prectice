package com.yeh35.jpaprectice.ex1hellojpa

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import javax.persistence.EntityManagerFactory

@Component
class StartRunner(
    val emf: EntityManagerFactory
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        insert()
        select()
//        emf.close()
    }

    private fun select() {
        val em = emf.createEntityManager()
        val tx = em.transaction
        tx.begin()

        try {
            val member = em.find(Member::class.java, 0L)
            println("member : ${member.name}")
            tx.commit()
        } catch (e: Exception) {
            tx.rollback()
        }

        em.close()
    }

    private fun insert() {
        val em = emf.createEntityManager()
        val tx = em.transaction
        tx.begin()

        try {
            val member = Member("hahaho")
            em.persist(member)
            tx.commit()
        } catch (e: Exception) {
            tx.rollback()
        }

        em.close()
    }

}