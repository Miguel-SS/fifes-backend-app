package com.backend.fifes

import org.mapstruct.*

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface RoleMapper {
    fun roleToRoleDetails(
        role: Role?
    ): RoleDetails

    fun roleListToRoleDetailsList(
        roleList: Set<Role>?
    ): Set<RoleDetails>
}

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface PrivilegeMapper {
    fun privilegeToPrivilegeDetails(
        privilege: Privilege
    ): PrivilegeDetails

    fun privilegeListToPrivilegeDetailsList(
        privilegeList: Set<Privilege>
    ): Set<PrivilegeDetails>
}

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface PlayerMapper {
    fun playerToPlayerResult(
        player: Player
    ): PlayerResult

    fun playerListToPlayerResultList(
        playerList: MutableList<Player>
    ): Set<PlayerResult>

    fun playerInputToPlayer(
        playerInput: PlayerInput
    ): Player

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun playerInputToPlayer(dto: PlayerInput, @MappingTarget player: Player)
}

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface StatsMapper {
    fun statsToStatsDetails(
        stats: Stats
    ): StatsDetails

    fun statsListToStatsDetailsList(
        statsList: Set<Stats>
    ): Set<StatsDetails>

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun statsDetailsToStats(dto: StatsDetails, @MappingTarget stats: Stats)
}