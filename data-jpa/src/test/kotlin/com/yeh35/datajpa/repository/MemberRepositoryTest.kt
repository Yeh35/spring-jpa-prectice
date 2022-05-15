package com.yeh35.datajpa.repository

import com.yeh35.datajpa.entity.Member
import com.yeh35.datajpa.entity.Team
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Slice
import org.springframework.data.domain.Sort
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Transactional
@SpringBootTest
internal class MemberRepositoryTest {

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var teamRepository: TeamRepository

    @PersistenceContext
    private lateinit var em: EntityManager

    @Test
    fun classCheck() {
        println("memberRepository: ${memberRepository::class.java}")
        println("teamRepository: ${teamRepository::class.java}")
    }

    @Test
    fun save() {
        val team = Team("Team")
        val member = Member("김삿갓", 15, team)
        val saveMember = memberRepository.save(member)

        val findMember = memberRepository.findById(saveMember.id).get()

        assertThat(findMember.id).isEqualTo(member.id)
        assertThat(findMember.username).isEqualTo(member.username)

        assertThat(findMember).isEqualTo(saveMember)
    }

    @Test
    fun findByUsernameAndAgeGreaterThan() {
        val team = Team("Team")
        val username = "김삿갓"
        val member = Member(username, 15, team)
        teamRepository.save(team)
        memberRepository.save(member)

        val memberList = memberRepository.findByUsernameAndAgeGreaterThan(username, 10)
        memberList.forEach {m ->
            println("member: ${m.username}, ${m.age}")
        }

    }

    @Test
    fun findMemberDto() {
        val team = Team("Team")
        teamRepository.save(team)

        val username = "김삿갓"
        val member = Member(username, 15, team)
        memberRepository.save(member)

        val memberDtoList = memberRepository.findMemberDto()
        memberDtoList.forEach {m ->
            println("member: ${m.id}, ${m.username}, ${m.teamName}")
        }
    }

    //페이징 조건과 정렬 조건 설정
    @Test
    @Throws(Exception::class)
    fun page() {
        //given
        memberRepository.save(Member("member1", 10))
        memberRepository.save(Member("member2", 10))
        memberRepository.save(Member("member3", 10))
        memberRepository.save(Member("member4", 10))
        memberRepository.save(Member("member5", 10))

        //when
        val pageRequest: PageRequest = PageRequest.of(
            0, 3, Sort.by(
                Sort.Direction.DESC,
                "username"
            )
        )

        val page: Page<Member> = memberRepository.findByAge(10, pageRequest)

        //then
        val content: List<Member> = page.content //조회된 데이터
        assertThat(content.size).isEqualTo(3) //조회된 데이터 수
        assertThat(page.totalElements).isEqualTo(5) //전체 데이터 수
        assertThat(page.number).isEqualTo(0) //페이지 번호
        assertThat(page.totalPages).isEqualTo(2) //전체 페이지 번호
        assertThat(page.isFirst).isTrue //첫번째 항목인가?
        assertThat(page.hasNext()).isTrue //다음 페이지가 있는가?
    }


    @Test
    @Throws(Exception::class)
    fun slice() {
        //given
        memberRepository.save(Member("member1", 10))
        memberRepository.save(Member("member2", 10))
        memberRepository.save(Member("member3", 10))
        memberRepository.save(Member("member4", 10))
        memberRepository.save(Member("member5", 10))

        //when
        val pageRequest: PageRequest = PageRequest.of(
            0, 3, Sort.by(
                Sort.Direction.DESC,
                "username"
            )
        )

        val page: Slice<Member> = memberRepository.findSliceByAge(10, pageRequest)

        //then
        val content: List<Member> = page.content //조회된 데이터
        assertThat(content.size).isEqualTo(3) //조회된 데이터 수
        assertThat(page.number).isEqualTo(0) //페이지 번호
        assertThat(page.isFirst).isTrue //첫번째 항목인가?
        assertThat(page.hasNext()).isTrue //다음 페이지가 있는가?
    }

    @Test
    fun testBaseEntity() {
        val teamA = Team("Team A")
        teamRepository.save(teamA)

        val member = Member("member", 10, teamA)
        memberRepository.save(member)

        Thread.sleep(100)
        member.changeUsername("member-ch")
        em.flush() //@PreUpdate
        em.clear()

        //when
        val findMember = memberRepository.findById(member.id).get()

        //then
        println("findMember.createdDate = " + findMember.createdDate)
        println("findMember.lastModifiedDate = " + findMember.lastModifiedDate)
        println("findMember.createdBy = " + findMember.createdBy)
        println("findMember.lastModifiedBy = " + findMember.lastModifiedBy)
    }
}