package com.backend.fifes

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.convert.ThreeTenBackPortConverters.DateToInstantConverter
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalTime
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
     * Find all Players with the ids
     * @return list
     */
    fun findAllById(players: List<Long>): Pair<Set<PlayerResult>, Set<PlayerResult>>?

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

    override fun findAllById(players: List<Long>): Pair<Set<PlayerResult>, Set<PlayerResult>>? {
        val playersList: MutableList<Player> = playerRepository.findAllById(players)

        playersList.sortByDescending { it.stats?.overall }

        var teamOne: MutableSet<PlayerResult> = mutableSetOf()
        var teamTwo: MutableSet<PlayerResult> = mutableSetOf()

        val playersIterator = playerMapper.playerListToPlayerResultList(playersList).iterator()
        while (playersIterator.hasNext()) {
            teamOne.add(playersIterator.next())
            if(playersIterator.hasNext()) teamTwo.add(playersIterator.next())
        }

        teamOne = teamOne.shuffled(kotlin.random.Random(LocalTime.now().second)).toMutableSet()
        teamTwo = teamTwo.shuffled(kotlin.random.Random(LocalTime.now().second)).toMutableSet()

        return Pair(teamOne, teamTwo)
    }

    @Throws(NoSuchElementException::class)
    override fun findById(id: Long): PlayerResult? {
        val player: Player = playerRepository.findById(id).orElse(null)
            ?: throw  NoSuchElementException(String.format("The player with the id: %s not found", id))
        return playerMapper.playerToPlayerResult(player)
    }

    override fun create(playerInput: PlayerInput): PlayerResult? {
        val player: Player = playerMapper.playerInputToPlayer(playerInput)

        if (player.stats != null) {
            player.stats?.overall = player.stats?.attack?.plus(player.stats!!.defense!!)?.plus(player.stats!!.stamina!!)
        }

        return playerMapper.playerToPlayerResult(
            playerRepository.save(player)
        )
    }

    override fun update(playerInput: PlayerInput): PlayerResult? {
        val player: Optional<Player> = playerRepository.findById(playerInput.id!!)

        val playerUpdate: Player = player.get()
        playerMapper.playerInputToPlayer(playerInput, playerUpdate)
        if(playerInput.stats != null) {

            playerUpdate.stats?.overall =
                playerInput.stats?.attack?.plus(playerInput.stats!!.defense!!)?.plus(playerInput.stats!!.stamina!!)

        }
        return playerMapper.playerToPlayerResult(playerRepository.save(playerUpdate))
    }

    override fun deleteById(id: Long) {
        playerRepository.findById(id).orElse(null)
            ?:throw NoSuchElementException(String.format("The Player with the id: %s not found", id))
        playerRepository.deleteById(id)
    }
}
