package com.example.partnerkintestapp.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ConferenceResult(
    @SerialName("view_type") val viewType: Int,
    @SerialName("conference") val conference: ConferenceDto
)