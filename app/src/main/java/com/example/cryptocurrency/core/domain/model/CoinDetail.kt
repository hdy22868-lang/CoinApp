package com.example.cryptocurrency.core.domain.model

import com.example.cryptocurrency.core.data.remote.dto.TeamMember
import kotlinx.serialization.Serializable

@Serializable
data class CoinDetail(
    val coinId: String,
    val name: String,
    val symbol : String,
    val rank : Int,
    val isActive : Boolean,
    val tags: List<String>? = emptyList(),
    val team: List<TeamMember>? = emptyList(),
    val description: String? = null,
)
