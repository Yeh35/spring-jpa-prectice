package com.yeh35.datajpa.repository

import com.yeh35.datajpa.entity.Team
import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepository : JpaRepository<Team, Long> {

}