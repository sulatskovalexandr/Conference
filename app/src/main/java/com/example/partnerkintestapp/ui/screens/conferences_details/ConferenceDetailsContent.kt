package com.example.partnerkintestapp.ui.screens.conferences_details

import android.icu.util.Calendar
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.partnerkintestapp.R
import com.example.partnerkintestapp.common.parseDate
import com.example.partnerkintestapp.data.model.RelatedConference
import com.example.partnerkintestapp.data.remote.dto.Category
import com.example.partnerkintestapp.ui.screens.items.CategoriesDetailsList
import com.example.partnerkintestapp.ui.screens.items.ItemRelatedEvent
import com.example.partnerkintestapp.ui.theme.ButtonBg
import com.example.partnerkintestapp.ui.theme.InterNormalFontFamily
import com.example.partnerkintestapp.ui.theme.InterSemiboldFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConferenceDetailsContent(
    state: State,
    onBackClick: () -> Unit,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.primary,
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        when (state) {
            is State.Conference -> {
                val scroll: ScrollState = rememberScrollState(0)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .verticalScroll(scroll)
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 12.dp),
                        text = state.typeName,
                        style = TextStyle(fontSize = 14.sp),
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = InterNormalFontFamily,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 4.dp),
                        text = state.name,
                        style = TextStyle(fontSize = 24.sp),
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = InterSemiboldFontFamily,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .clip(shape = RoundedCornerShape(12.dp)),
                        model = state.imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    CategoriesDetailsList(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        categories = state.categories
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        val startCalendar = parseDate(state.startDate)
                        val endCalendar = parseDate(state.endDate)
                        val monthArray = stringArrayResource(R.array.eventMonths)
                        Icon(
                            modifier = Modifier.size(24.dp),
                            contentDescription = "",
                            tint = ButtonBg,
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_schedule),
                        )
                        Text(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            text = buildString {
                                append("${startCalendar.get(Calendar.DAY_OF_MONTH)} ")
                                append("${monthArray[startCalendar.get(java.util.Calendar.MONTH)]} ")
                                append("${startCalendar.get(Calendar.YEAR)}, ")

                                val days =
                                    endCalendar.get(Calendar.DAY_OF_MONTH) - startCalendar.get(
                                        Calendar.DAY_OF_MONTH,
                                    )
                                append(
                                    pluralStringResource(
                                        id = R.plurals.plurals_day,
                                        count = days,
                                        formatArgs = arrayOf(days)
                                    )
                                )
                            },
                            style = TextStyle(fontSize = 16.sp),
                            color = MaterialTheme.colorScheme.primary,
                            fontFamily = InterSemiboldFontFamily,
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            contentDescription = "",
                            tint = ButtonBg,
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_location),
                        )
                        Text(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            text = "${state.city}, ${state.country}",
                            style = TextStyle(fontSize = 16.sp),
                            color = MaterialTheme.colorScheme.primary,
                            fontFamily = InterSemiboldFontFamily
                        )
                    }
                    Spacer(modifier = Modifier.height(28.dp))

                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ButtonBg,
                            contentColor = MaterialTheme.colorScheme.background
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp)
                            .padding(horizontal = 16.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = stringResource(R.string.button_registration_text),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = stringResource(R.string.related_evetns),
                        style = TextStyle(fontSize = 18.sp),
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = InterSemiboldFontFamily,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.SemiBold,
                    )

                    ItemRelatedEvent(relatedConference = state.relatedConferences[0]) { }
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .padding(horizontal = 16.dp)
                            .background(color = MaterialTheme.colorScheme.primaryContainer)
                    )
                    ItemRelatedEvent(relatedConference = state.relatedConferences[1]) { }
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .padding(horizontal = 16.dp)
                            .background(color = MaterialTheme.colorScheme.primaryContainer)
                    )
                    ItemRelatedEvent(relatedConference = state.relatedConferences[2]) { }

                    Spacer(modifier = Modifier.height(48.dp))

                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = stringResource(R.string.about_event),
                        style = TextStyle(fontSize = 18.sp),
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = InterSemiboldFontFamily,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = state.about,
                        style = TextStyle(fontSize = 15.sp),
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = InterNormalFontFamily,
                        fontStyle = FontStyle.Normal,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                }
            }

            is State.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center),
                    )
                }
            }

            is State.Error -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center),
                        text = stringResource(R.string.load_error)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewConferenceDetailsContent() {
    ConferenceDetailsContent(
        state = State.Conference(
            id = 3228,
            name = "SiGMA Africa 2025",
            format = "offline",
            status = "publish",
            statusTitle = "Опубликована",
            url = "sigma-africa-2025",
            imageUrl = "https://partnerkin.com/uploads/webp/conferences/81e62a7f948c48175e7d5eaa.webp",

            rating = "",
            relatedConferences = listOf(
                RelatedConference(id = 1, title = "ЛАС-ВЕГАС ЯНВ ’24", rating = 5f, isNew = true),
                RelatedConference(id = 2, title = "ЛАС-ВЕГАС ЯНВ ’24", rating = 5f, isNew = false),
                RelatedConference(id = 3, title = "ЛАС-ВЕГАС ЯНВ ’23", rating = 8.3f, isNew = false)
            ),
            startDate = "2025-03-10",
            endDate = "2025-03-12",
            country = "ЮАР",
            city = "Кейптаун",
            categories = listOf(
                Category(
                    id = 4,
                    name = "Affiliate",
                    url = "affiliate"
                ),
                Category(
                    id = 4,
                    name = "Affiliate",
                    url = "affiliate"
                ),
                Category(
                    id = 4,
                    name = "Affiliate",
                    url = "affiliate"
                ),
                Category(
                    id = 4,
                    name = "Affiliate",
                    url = "affiliate"
                ),
            ),
            typeName = "Конференция",
            registerUrl = "https://partnerkin.com/events/sigma-africa-2025",
            about = "SiGMA Africa 2025 пройдет с 10 по 12 марта в Кейптауне на территории Sun Exhibits. Организаторы планируют собрать 2500 представителей гемблинг и беттинг ниш, включая аффилиатов, операторов и поставщиков услуг."
        ),
    ) {}
}