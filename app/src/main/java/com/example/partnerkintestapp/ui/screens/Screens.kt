package com.example.partnerkintestapp.ui.screens

import kotlinx.serialization.Serializable

sealed interface Screens {

    @Serializable
    data object Conferences : Screens

    @Serializable
    data object ConferenceDetails : Screens
}