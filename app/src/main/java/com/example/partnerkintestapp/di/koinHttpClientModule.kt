package com.example.partnerkintestapp.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.http.encodeURLParameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val koinHttpClientModule = module {
    single<HttpClient> {
        HttpClient(OkHttp) {

            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "partnerkin.com/"
                    parameters.append("api_key", "DMwdj29q@S29shslok2")
                }
            }
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }
    }
}