package com.example.partnerkintestapp.data.remote.dto

import com.example.partnerkintestapp.data.model.ConferenceDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ViewApiResponse(
    @SerialName("error") val error: ErrorResponse?,
    @SerialName("data") val data: ConferenceDetailsDto?
)