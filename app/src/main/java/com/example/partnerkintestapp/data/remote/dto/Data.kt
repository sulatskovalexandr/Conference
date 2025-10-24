package com.example.partnerkintestapp.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class Data(
    @SerialName("counts") val counts: Int,
    @SerialName("pagination") val pagination: Pagination,
    @SerialName("result") val result: List<ConferenceResult>
)