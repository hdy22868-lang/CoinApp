package com.example.cryptocurrency.core.data.remote.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class LinksExtended(
    val stats: JsonElement? = null,
    val type: String,
    val url: String
)