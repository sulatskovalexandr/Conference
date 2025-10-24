package com.example.partnerkintestapp.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Image(
    @SerialName("id") val id: String,
    @SerialName("url") val url: String,
    @SerialName("preview") val preview: String,
    @SerialName("placeholder_color") val placeholderColor: String? = null,
    @SerialName("width") val width: Int,
    @SerialName("height") val height: Int

)