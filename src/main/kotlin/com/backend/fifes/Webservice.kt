package com.backend.fifes

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import kotlin.jvm.Throws

@RestController
@RequestMapping("\${url.players}")
class PlayerController(private val playerService: PlayerService) {
    /**
     * WS to find all elements of type Player
     * @return A List of elements of type Player
     */
    @GetMapping
    @ResponseBody
    fun findAll() = playerService.findAll()

    /**
     * WS to fin all elements of type Player by id
     * @return A List of elements of type Player
     */
    @Throws(NoSuchElementException::class)
    @GetMapping("{playersId}")
    @ResponseBody
    fun findAllById(@PathVariable playersId: List<Long>) = playerService.findAllById(playersId)

    // /**
    // * WS to find one Player by the id
    // * @param id to find Player
    // * @return the Player found
    // */
    // @Throws(NoSuchElementException::class)
    // @GetMapping("{id}")
    // @ResponseBody
    // fun findById(@PathVariable id: Long) = playerService.findById(id)

    /**
     * WS to create a Player
     * @param playerInput
     * @return the Player created
     */
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun create(@RequestBody playerInput: PlayerInput): PlayerResult? {
        return playerService.create(playerInput)
    }

    /**
     * WS to update a Player
     * @param playerInput
     * @return the Player updated
     */
    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun update(@RequestBody playerInput: PlayerInput): PlayerResult? {
        return playerService.update(playerInput)
    }

    /**
     * WS to delete a Player
     * @param id
     */
    @Throws(NoSuchElementException::class)
    @DeleteMapping("{id}")
    @ResponseBody
    fun deleteById(@PathVariable id: Long) {
        playerService.deleteById(id)
    }
}
