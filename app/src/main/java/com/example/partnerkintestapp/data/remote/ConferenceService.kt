package com.example.partnerkintestapp.data.remote

import com.example.partnerkintestapp.data.remote.dto.ListApiResponse
import com.example.partnerkintestapp.data.remote.dto.ViewApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ConferenceService(
    private val client: HttpClient
) {

    suspend fun getConferences(): ListApiResponse =
        client.get("api_ios_test/list").body()

    suspend fun getConference(): ViewApiResponse =
        client.get("/api_ios_test/view").body()
}