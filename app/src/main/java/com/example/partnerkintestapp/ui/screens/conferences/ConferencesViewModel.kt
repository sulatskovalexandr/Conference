package com.example.partnerkintestapp.ui.screens.conferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.partnerkintestapp.data.paging.ConferencePagingSource
import com.example.partnerkintestapp.data.repository.ConferenceRepository

class ConferencesViewModel(
    private val conferenceRepository: ConferenceRepository
) : ViewModel() {

    val listConference = Pager(
        config = PagingConfig(
            pageSize = 5,
            prefetchDistance = 1,
            enablePlaceholders = true,
        )
    ) {
        ConferencePagingSource(conferenceRepository)
    }.flow.cachedIn(viewModelScope)

}

