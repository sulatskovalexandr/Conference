package com.example.partnerkintestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.partnerkintestapp.di.koinConferenceModule
import com.example.partnerkintestapp.di.koinHttpClientModule
import com.example.partnerkintestapp.di.koinRepositoryModule
import com.example.partnerkintestapp.ui.screens.Screens
import com.example.partnerkintestapp.ui.screens.conferences.ConferencesScreen
import com.example.partnerkintestapp.ui.screens.conferences_details.ConferenceDetailsScreen
import com.example.partnerkintestapp.ui.theme.PartnerkinTestAppTheme
import org.koin.compose.KoinApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PartnerkinTestAppTheme {
                KoinApplication(
                    application = {
                        modules(
                            koinConferenceModule,
                            koinHttpClientModule,
                            koinRepositoryModule
                        )
                    },
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screens.Conferences,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        ConferencesScreen(navController)
                        ConferenceDetailsScreen(navController)
                    }
                }
            }
        }
    }
}

