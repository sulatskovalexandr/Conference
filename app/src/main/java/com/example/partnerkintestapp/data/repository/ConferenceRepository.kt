package com.example.partnerkintestapp.data.repository

import com.example.partnerkintestapp.data.model.ConferenceDetails
import com.example.partnerkintestapp.data.model.RelatedConference
import com.example.partnerkintestapp.data.remote.ConferenceService
import com.example.partnerkintestapp.data.remote.dto.ListApiResponse
import com.example.partnerkintestapp.data.remote.dto.toModel

class ConferenceRepository(private val conferenceService: ConferenceService) {

    suspend fun getConferences(): ListApiResponse = conferenceService.getConferences()

    suspend fun getConference(): ConferenceDetails? =
        conferenceService.getConference().data?.toModel()

    fun getRelatedConferenceList() = listOf(
        RelatedConference(id = 1, title = "ЛАС-ВЕГАС ЯНВ ’24", rating = null, isNew = true),
        RelatedConference(id = 2, title = "ЛАС-ВЕГАС ЯНВ ’24", rating = 5f, isNew = false),
        RelatedConference(id = 3, title = "ЛАС-ВЕГАС ЯНВ ’23", rating = 8.3f, isNew = false)
    )
}