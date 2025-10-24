package com.example.partnerkintestapp.data.model

data class RelatedConference(
    val id: Int,
    val title: String,
    val rating: Float? = null,
    val isNew: Boolean
)