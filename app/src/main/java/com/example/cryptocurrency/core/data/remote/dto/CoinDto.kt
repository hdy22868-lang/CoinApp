package com.example.cryptocurrency.core.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinDto(
    val id: String,
    @SerialName("is_new")
    val isNew: Boolean,
    @SerialName("is_active")
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)