package com.yeh35.datajpa.repository

import com.yeh35.datajpa.dto.MemberDto
import com.yeh35.datajpa.entity.Member
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param


interface MemberRepository : JpaRepository<Member, Long> {

    fun findByUsernameAndAgeGreaterThan(username: String, age: Int): List<Member>

    @Query("select m from Member m where m.username= :username and m.age = :age")
    fun findUser(@Param("username") username: String?, @Param("age") age: Int): List<Member?>?

    @Query("SELECT m.username from Member m")
    fun findUsernameList(): List<String>

//    @Query("SELECT m.id, m.username, m.team.name from Member m join m.team")
    @Query("SELECT new com.yeh35.datajpa.dto.MemberDto(m.id, m.username, m.team.name) from Member m join m.team")
    fun findMemberDto(): List<MemberDto>

    fun findByAge(age: Int, pageable: Pageable): Page<Member>

    fun findSliceByAge(age: Int, pageable: Pageable): Slice<Member>

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    fun bulkAgePlus(@Param("age") age: Int): Int

    /** 공통 메서드 오버라이드 */
    @Override
    @EntityGraph(attributePaths = ["team"])
    override fun findAll(): List<Member>

    /** JPQL + 엔티티 그래프 */
    @EntityGraph(attributePaths = ["team"])
    @Query("select m from Member m")
    fun findMemberEntityGraph(): List<Member>

    /** 메서드 이름으로 쿼리에서 특히 편리하다. */
    @EntityGraph(attributePaths = ["team"])
    fun findByUsername(username: String): List<Member>
}