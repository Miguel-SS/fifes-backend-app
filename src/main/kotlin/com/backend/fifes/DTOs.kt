package com.backend.fifes

import java.util.*

// -------------------- User entity --------------------
data class UserInput(
    var id: Long? = null,
    var name: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var password: String? = null,
    var enabled: Boolean? = null,
    var roles: List<RoleDetails>? = null,
)

data class UserResult(
    var id: Long,
    var name: String?,
    var lastName: String?,
    var email: String,
    var password: String,
    var enabled: Boolean?,
    var createdDate: Date?,
    var roles: List<RoleDetails>?,
)
data class UserLoginInput(
    var username: String = "",
    var password: String = ""
)

data class UserSignUpInput(
    var name: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var password: String? = null,
)

// -------------------- Role entity --------------------
data class RoleDetails(
    var id: Long? = null,
    var name: String? = null
)

// -------------------- Privilege entity --------------------
data class PrivilegeDetails(
    var id: Long? = null,
    var name: String? = null
)

// -------------------- Player entity --------------------
data class PlayerInput(
    var id: Long? = null,
    var name: String? = null,
    var stats: Stats? = null
)

data class PlayerResult(
    var id: Long,
    var name: String? = null,
    var stats: Stats? = null
)

// -------------------- Stats entity --------------------
data class StatsDetails(
    var id: Long? = null,
    var attack: Long? = null,
    var defense: Long? = null,
    var stamina: Long? = null
)