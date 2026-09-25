package com.example.cryptocurrency.core.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Stats(
    val contributors: Int,
    val followers: Int,
    val stars: Int,
    val subscribers: Int
)