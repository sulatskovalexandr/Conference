package com.example.partnerkintestapp.ui.screens.conferences

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.partnerkintestapp.R
import com.example.partnerkintestapp.data.paging.ConferenceListItem
import com.example.partnerkintestapp.ui.screens.items.ItemConference
import com.example.partnerkintestapp.ui.screens.items.ItemMonth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConferencesContent(
    conferencesList: LazyPagingItems<ConferenceListItem>,
    onConferenceClick: () -> Unit,
) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.comferences_toolbar_title),
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_support),
                            contentDescription = "Support",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },

        ) { innerPadding ->
        ListConferences(innerPadding = innerPadding, conferencesList, onConferenceClick)
    }
}

@Composable
fun ListConferences(
    innerPadding: PaddingValues,
    conferencesList: LazyPagingItems<ConferenceListItem>,
    onConferenceClick: () -> Unit
) {
    val isRefreshing = conferencesList.loadState.refresh is LoadState.Loading
    val state = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { conferencesList.refresh() },
        state = state,
        indicator = {
            Indicator(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 90.dp),
                isRefreshing = isRefreshing,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                color = MaterialTheme.colorScheme.primary,
                state = state
            )
        },
    ) {
        if(conferencesList.loadState.refresh is LoadState.Error){
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = stringResource(R.string.load_error)
                )
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.spacedBy(8.dp)

        ) {
            if (conferencesList.loadState.refresh != LoadState.Loading) {
                items(conferencesList.itemCount) { index ->
                    conferencesList[index]?.let { item ->
                        when (item) {
                            is ConferenceListItem.MonthItem -> {
                                ItemMonth(item)
                            }

                            is ConferenceListItem.ConferenceItem -> {
                                ItemConference(item, onConferenceClick)
                            }
                        }
                    }
                }
            }
        }
    }
}