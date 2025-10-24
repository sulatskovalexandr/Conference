package com.example.partnerkintestapp.data.remote.dto

import com.example.partnerkintestapp.data.model.ConferenceDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ConferenceDetailsDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("format") val format: String,
    @SerialName("status") val status: String,
    @SerialName("status_title") val statusTitle: String,
    @SerialName("url") val url: String,
    @SerialName("image") val image: Image,
    @SerialName("rating") val rating: String? = null,
    @SerialName("start_date") val startDate: String,
    @SerialName("end_date") val endDate: String,
    @SerialName("oneday") val oneDay: Int,
    @SerialName("custom_date") val customDate: String? = null,
    @SerialName("country_id") val countryId: Int,
    @SerialName("country") val country: String,
    @SerialName("city_id") val cityId: Int,
    @SerialName("city") val city: String,
    @SerialName("categories") val categories: List<Category>,
    @SerialName("type_id") val typeId: Int,
    @SerialName("type") val type: Type,
    @SerialName("register_url") val registerUrl: String,
    @SerialName("about") val about: String,

)

fun ConferenceDetailsDto.toModel(): ConferenceDetails =
    ConferenceDetails(
        id = id,
        name = name,
        format = format,
        status = status,
        statusTitle = statusTitle,
        url = url,
        image = image,
        rating = rating.orEmpty(),
        startDate = startDate,
        endDate = endDate,
        oneDay = oneDay,
        customDate = customDate.orEmpty(),
        countryId = countryId,
        country = country,
        cityId = cityId,
        city = city,
        categories = categories,
        typeId = typeId,
        type = type,
        registerUrl =registerUrl,
        about = about
    )
