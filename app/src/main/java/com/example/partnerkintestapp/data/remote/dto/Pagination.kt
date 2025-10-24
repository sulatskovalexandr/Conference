package com.example.partnerkintestapp.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Pagination(
    @SerialName("page_size") val pageSize: Int,
    @SerialName("current_page") val currentPage: Int
)