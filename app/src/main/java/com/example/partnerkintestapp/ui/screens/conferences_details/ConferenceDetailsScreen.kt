package com.example.partnerkintestapp.ui.screens.conferences_details

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.partnerkintestapp.ui.screens.Screens
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.ConferenceDetailsScreen(
    navController: NavController,
) = composable<Screens.ConferenceDetails> {
    val viewModel: ConferenceDetailsViewModel = koinViewModel()
    val state by viewModel.state.collectAsState(State.Loading)

        ConferenceDetailsContent(state) {
            navController.navigateUp()
        }
}