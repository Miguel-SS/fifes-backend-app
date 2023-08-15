package com.backend.fifes

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.test.context.jdbc.Sql
import java.util.*
import javax.transaction.Transactional

@Profile ("local")

@Transactional
@SpringBootTest
@Sql("/import-users.sql", "/import-players.sql")
class LoadInitData (
    @Autowired
    val privilegeRepository: PrivilegeRepository,
    @Autowired
    val roleRepository: RoleRepository,
    @Autowired
    val userRepository: UserRepository,
    @Autowired
    val playerRepository: PlayerRepository,
    @Autowired
    val statsRepository: StatsRepository
)
{
    // ***************** FIND ALL *****************
    @Test
    fun testPrivilegeFindAll() {
        val privilegeList: List<Privilege> = privilegeRepository.findAll()
        Assertions.assertTrue(privilegeList.size == 2)
    }

    @Test
    fun testRoleFindAll() {
        val roleList: List<Role> = roleRepository.findAll()
        Assertions.assertTrue(roleList.size == 3)
    }

    @Test
    fun testUserFindAll() {
        val userList: List<User> = userRepository.findAll()
        Assertions.assertTrue(userList.size == 2)
    }


    @Test
    fun testPlayerFindAll() {
        val playerList: List<Player> = playerRepository.findAll()
        Assertions.assertTrue(playerList.size == 3)
    }

    @Test
    fun testStatsFindAll() {
        val statsList: List<Stats> = statsRepository.findAll()
        Assertions.assertTrue(statsList.size == 3)
    }

    // ***************** FIND BY *****************
    @Test
    fun findUserByEmail() {
        val user: Optional<User> = userRepository.findByEmail("miguel@email.com")
        Assertions.assertTrue(user.get().id?.toInt() == 1)
    }


    // ***************** CREATE *****************
    @Test
    fun createPlayer() {
        val stats = Stats(null, 15, 15, 15)
        val player = Player(null, "Pablo Montagnini", stats)
        val playerCreated = playerRepository.save(player)
        Assertions.assertTrue(playerCreated.firstName == "Pablo Montagnini")
    }

    // ***************** UPDATE *****************
    @Test
    fun updatePlayer() {
        val playerUpdate: Player = playerRepository.findAll()[0]
        playerUpdate.firstName = "Miguel Salas"
        playerRepository.save(playerUpdate)
        val newPlayerUpdate: Optional<Player> = playerRepository.findById(playerUpdate.id!!)
        Assertions.assertTrue(newPlayerUpdate.get().firstName == "Miguel Salas")
    }

    @Test
    fun updateStats() {
        val statsUpdate: Stats = statsRepository.findAll()[0]
        statsUpdate.attack = 99
        statsRepository.save(statsUpdate)
        val newStatsUpdate: Optional<Stats> = statsRepository.findById(statsUpdate.id!!)
        Assertions.assertTrue(newStatsUpdate.get().attack?.toInt() == 99)
    }

}