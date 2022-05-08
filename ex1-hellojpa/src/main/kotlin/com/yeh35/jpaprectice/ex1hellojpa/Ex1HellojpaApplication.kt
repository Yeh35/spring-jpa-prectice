package com.yeh35.jpaprectice.ex1hellojpa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Ex1HellojpaApplication

fun main(args: Array<String>) {
    runApplication<Ex1HellojpaApplication>(*args)
}


//fun main(args: Array<String>) {
//    //EntityManagerFactory는 시스템 전체에서 하나만!!
//    val emf = Persistence.createEntityManagerFactory("<설정 정보 이름>")
//
//    // EntityManager는 하나의 쿼리에서 하나만 여러 쓰레드에서 공유하면 안됨!
//    val em = emf.createEntityManager()
//
//    val tx = em.transaction
//    tx.begin()
//
//    try {
//        // 저장
//        val member = Member("hahaho")
//        em.persist(member)
//
//        // 읽기
//        val loadMember = em.find(Member::class.java, 0L)
//        println("member : ${member.name}")
//
//        // JPQL 쿼리 (쿼리 대상이 테이블이 아니라 개체이다)
//        val memberList = em.createQuery("SELECT m FROM Member as m", Member::class.java)
//            .setFirstResult(5).setMaxResults(10) // 5번부터 10개 가져와 (페이징)
//            .resultList
//        memberList.stream().forEach { m ->
//            println("member : ${m.name}")
//        }
//
//        tx.commit()
//    } catch (e: Exception) {
//        tx.rollback()
//    }
//
//    em.close()
//    emf.close()
//}