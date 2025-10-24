package com.example.partnerkintestapp.ui.screens.conferences_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partnerkintestapp.data.model.RelatedConference
import com.example.partnerkintestapp.data.remote.dto.Category
import com.example.partnerkintestapp.data.repository.ConferenceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ConferenceDetailsViewModel(
    private val conferenceRepository: ConferenceRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state: Flow<State> = _state.asStateFlow()

    init {
        getConferenceDetails()
    }

    private fun getConferenceDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val conf = conferenceRepository.getConference()
                val relatedConf = conferenceRepository.getRelatedConferenceList()
                if (conf != null) {
                    _state.update {
                        State.Conference(
                            id = conf.id,
                            name = conf.name,
                            format = conf.format,
                            status = conf.status,
                            statusTitle = conf.statusTitle,
                            url = conf.url,
                            imageUrl = conf.image.url,
                            rating = conf.rating,
                            relatedConferences = relatedConf,
                            startDate = conf.startDate,
                            endDate = conf.endDate,
                            country = conf.country,
                            city = conf.city,
                            categories = conf.categories,
                            typeName = conf.type.name,
                            registerUrl = conf.registerUrl,
                            about = conf.about
                        )
                    }
                } else {
                    _state.update { State.Error }
                }

            } catch (t: Throwable) {
                t.printStackTrace()
                _state.update { State.Error }
            }
        }
    }
}

sealed interface State {
    data object Loading : State
    data class Conference(
        val id: Int,
        val name: String,
        val format: String,
        val status: String,
        val statusTitle: String,
        val url: String,
        val imageUrl: String,
        val rating: String,
        val relatedConferences: List<RelatedConference>,
        val startDate: String,
        val endDate: String,
        val country: String,
        val city: String,
        val categories: List<Category>,
        val typeName: String,
        val registerUrl: String,
        val about: String
    ) : State

    data object Error : State
}