package com.example.cryptocurrency.core.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Links(
    val explorer: List<String>,
    val facebook: List<String>,
    val reddit: List<String>,
    val source_code: List<String>,
    val website: List<String>,
    val youtube: List<String>? = null
)