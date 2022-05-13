package com.yeh35.datajpa.repository

import com.yeh35.datajpa.entity.Member
import org.springframework.data.jpa.repository.JpaRepository


interface MemberRepository : JpaRepository<Member, Long> {
}