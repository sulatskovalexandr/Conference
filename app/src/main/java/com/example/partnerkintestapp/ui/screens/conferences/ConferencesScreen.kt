package com.example.partnerkintestapp.ui.screens.conferences

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.partnerkintestapp.data.paging.ConferenceListItem
import com.example.partnerkintestapp.ui.screens.Screens
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.ConferencesScreen(
    navController: NavController,
) = composable<Screens.Conferences> {
    val viewModel: ConferencesViewModel = koinViewModel()
    val conferenceList: LazyPagingItems<ConferenceListItem> =
        viewModel.listConference.collectAsLazyPagingItems()

    ConferencesContent(
        conferencesList = conferenceList,
        onConferenceClick = {
            navController.navigate(Screens.ConferenceDetails)
        }
    )
}