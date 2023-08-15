package com.backend.fifes

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.NoSuchElementException
import kotlin.jvm.Throws

interface PlayerService {
    /**
     * Find all Players
     * @return list
     */
    fun findAll(): Set<PlayerResult>?

    /**
     * Get one Player by id
     * @param id of the Player
     * @return the Player found
     */
    fun findById(id: Long): PlayerResult?

    /**
     * Save and flush a Player entity in the database
     * @param playerInput
     * @return the Player created
     */
    fun create(playerInput: PlayerInput): PlayerResult?

    /**
     * Update and flush a Player entity in the database
     * @param playerInput
     * @return the Player created
     */
    fun update(playerInput: PlayerInput): PlayerResult?

    /**
     * Delete a Player by id from the database
     * @param id of the Player
     */
    fun deleteById(id: Long)
}

@Service
class AbstractPlayerService(
    @Autowired
    val playerRepository: PlayerRepository,
    @Autowired
    val statsRepository: StatsRepository,
    @Autowired
    val playerMapper: PlayerMapper,
) : PlayerService {
    override fun findAll(): Set<PlayerResult>? {
        return playerMapper.playerListToPlayerResultList(
            playerRepository.findAll()
        )
    }

    @Throws(NoSuchElementException::class)
    override fun findById(id: Long): PlayerResult? {
        val player: Player = playerRepository.findById(id).orElse(null)
            ?: throw  NoSuchElementException(String.format("The player with the id: %s not found", id))
        return playerMapper.playerToPlayerResult(player)
    }

    override fun create(playerInput: PlayerInput): PlayerResult? {
        val player: Player = playerMapper.playerInputToPlayer(playerInput)
        return playerMapper.playerToPlayerResult(
            playerRepository.save(player)
        )
    }

    override fun update(playerInput: PlayerInput): PlayerResult? {
        val player: Optional<Player> = playerRepository.findById(playerInput.id!!)

        val playerUpdate: Player = player.get()
        playerMapper.playerInputToPlayer(playerInput, playerUpdate)
        if(playerInput.stats != null) {
            val stats: Optional<Stats> = statsRepository.findById(playerInput.stats?.id!!)
            if(stats.get().id != playerUpdate.stats?.id) {
                // playerRepository.save(playerUpdate)
                TODO("Test update")
            }
        }
        return playerMapper.playerToPlayerResult(playerRepository.save(playerUpdate))
    }

    override fun deleteById(id: Long) {
        playerRepository.findById(id).orElse(null)
            ?:throw NoSuchElementException(String.format("The Player with the id: %s not found", id))
        playerRepository.deleteById(id)
    }
}
