package com.example.cryptocurrency.core.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class TeamMember(
    val id: String,
    val name: String,
    val position: String
)