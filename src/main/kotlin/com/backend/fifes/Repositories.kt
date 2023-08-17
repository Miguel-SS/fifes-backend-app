package com.backend.fifes

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*
import javax.transaction.Transactional

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
interface PlayerRepository : JpaRepository<Player, Long> {
    @Transactional
    @Modifying
    @Query(
        value = "SELECT * FROM public.player WHERE id IN :ids",
        nativeQuery = true
    )
    fun findAllById(@Param("ids") ids: List<Long>): MutableList<Player>
}

@Repository
interface StatsRepository : JpaRepository<Stats, Long>
