package com.example.partnerkintestapp.di

import com.example.partnerkintestapp.data.remote.ConferenceService
import com.example.partnerkintestapp.data.repository.ConferenceRepository
import org.koin.dsl.module

val koinRepositoryModule = module {
    single {
        ConferenceRepository(conferenceService = get<ConferenceService>())
    }
}