package com.example.partnerkintestapp.di

import com.example.partnerkintestapp.data.remote.ConferenceService
import com.example.partnerkintestapp.data.repository.ConferenceRepository
import com.example.partnerkintestapp.ui.screens.conferences.ConferencesViewModel
import com.example.partnerkintestapp.ui.screens.conferences_details.ConferenceDetailsViewModel
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val koinConferenceModule = module {
    factory {
        ConferenceService(client = get<HttpClient>())
    }
    viewModel { ConferencesViewModel(get<ConferenceRepository>()) }
    viewModel { ConferenceDetailsViewModel(get<ConferenceRepository>()) }
}