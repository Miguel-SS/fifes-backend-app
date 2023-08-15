package com.backend.fifes

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(@Param("email") email: String) : Optional<User>
}

@Repository
interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(@Param("name") name: String): Optional<Role>
}

@Repository
interface PrivilegeRepository : JpaRepository<Privilege, Long>

@Repository
interface PlayerRepository : JpaRepository<Player, Long>

@Repository
interface StatsRepository : JpaRepository<Stats, Long>
