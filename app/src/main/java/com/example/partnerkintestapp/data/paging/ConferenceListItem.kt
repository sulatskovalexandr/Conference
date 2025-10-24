package com.example.partnerkintestapp.data.paging

import com.example.partnerkintestapp.data.remote.dto.Category
import com.example.partnerkintestapp.data.remote.dto.Image
import com.example.partnerkintestapp.data.remote.dto.Type

sealed class ConferenceListItem {
    data class ConferenceItem(
        val id: Int,
        val name: String,
        val format: String,
        val status: String,
        val statusTitle: String,
        val url: String,
        val image: Image,
        val rating: String,
        val startDate: String,
        val endDate: String,
        val oneDay: Int,
        val customDate: String,
        val countryId: Int,
        val country: String,
        val cityId: Int,
        val city: String,
        val categories: List<Category>,
        val typeId: Int,
        val type: Type,
    ) : ConferenceListItem()

    data class MonthItem(val startDate: String) : ConferenceListItem()
}