package com.yeh35.datajpa.controller

import com.yeh35.datajpa.entity.Member
import com.yeh35.datajpa.repository.MemberRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(val memberRepository: MemberRepository) {

    @GetMapping("/members/{id}")
    fun findMember(@PathVariable("id") id: Long): String {
        val member: Member = memberRepository!!.findById(id).get()
        return member.username
    }

    @GetMapping("/members2/{id}")
    fun findMember2(@PathVariable("id") member: Member): String {
        return member.username
    }

    @GetMapping("/members")
    fun list(
        @PageableDefault(
            size = 12,
            sort = ["username"],
            direction = Sort.Direction.DESC
        ) pageable: Pageable
    ): Page<MemberDto> {
        val page = memberRepository.findAll(pageable)
        return page.map { m -> MemberDto.of(m) }
    }
}


data class MemberDto (
    val id: Long,
    val username: String
) {
    companion object {
        fun of(m: Member): MemberDto {
            return MemberDto(m.id, m.username)
        }
    }
}
