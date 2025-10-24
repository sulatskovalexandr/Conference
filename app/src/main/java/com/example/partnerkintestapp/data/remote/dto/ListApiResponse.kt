package com.example.partnerkintestapp.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ListApiResponse(
    @SerialName("error") val error: ErrorResponse? = null,
    @SerialName("data") val data: Data?
)

@Serializable
data class ErrorResponse(
    val code: Int,
    val message: String
)