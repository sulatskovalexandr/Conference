package com.example.partnerkintestapp.ui.screens.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringArrayResource
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
import com.example.partnerkintestapp.data.paging.ConferenceListItem
import com.example.partnerkintestapp.data.paging.StatusTitle
import com.example.partnerkintestapp.data.remote.dto.Category
import com.example.partnerkintestapp.data.remote.dto.Type
import com.example.partnerkintestapp.ui.theme.CanceledPrimaryContainer
import com.example.partnerkintestapp.ui.theme.InterLightFontFamily
import com.example.partnerkintestapp.ui.theme.InterNormalFontFamily
import com.example.partnerkintestapp.ui.theme.InterSemiboldFontFamily
import com.example.partnerkintestapp.ui.theme.OnCanceledPrimaryContainer
import com.example.partnerkintestapp.ui.theme.PartnerkinTestAppTheme
import java.util.Calendar

@Composable
fun ItemConference(conference: ConferenceListItem.ConferenceItem, onConfClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(
                onClick = onConfClick
            )
            .background(
                color =
                    if (conference.status == StatusTitle.CANCELED.status) {
                        CanceledPrimaryContainer
                    } else {
                        MaterialTheme.colorScheme.primaryContainer
                    },
                shape = RoundedCornerShape(16.dp),
            )
            .padding(horizontal = 16.dp)
    ) {
        if (conference.status == StatusTitle.CANCELED.status) {
            Row(
                modifier =
                    Modifier
                        .padding(top = 10.dp)
                        .background(
                            color =
                                OnCanceledPrimaryContainer
                        )
                        .border(
                            1.dp,
                            color = MaterialTheme.colorScheme.inversePrimary,
                            shape = RoundedCornerShape(50.dp)
                        )
                        .padding(vertical = 5.dp, horizontal = 10.dp)
            ) {

                Image(
                    modifier = Modifier,
                    contentDescription = "",
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_lightning)
                )

                Text(
                    modifier = Modifier,
                    text = conference.statusTitle,
                    style = TextStyle(fontSize = 11.sp),
                    color = MaterialTheme.colorScheme.inversePrimary,
                    fontFamily = InterNormalFontFamily,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = conference.name,
            style = TextStyle(fontSize = 24.sp),
            color = MaterialTheme.colorScheme.primary,
            fontFamily = InterSemiboldFontFamily,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.SemiBold,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(104.dp)
                .padding(top = 20.dp)
                .background(
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.04f),
                    shape = RoundedCornerShape(12.dp)
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)),
                model = conference.image.url,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.Center),

                    ) {
                    val startCalendar = parseDate(conference.startDate)
                    val endCalendar = parseDate(conference.endDate)
                    val monthArray = stringArrayResource(R.array.monthsEng)
                    Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                        Text(
                            modifier = Modifier,
                            text = startCalendar.get(Calendar.DAY_OF_MONTH)
                                .toString(),
                            style = TextStyle(fontSize = 40.sp),
                            color = MaterialTheme.colorScheme.primary,
                            fontFamily = InterLightFontFamily,
                            fontWeight = FontWeight.ExtraLight,
                        )
                        Text(
                            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                            text = monthArray[startCalendar.get(Calendar.MONTH)],
                            style = TextStyle(fontSize = 12.sp),
                            color = MaterialTheme.colorScheme.primary,
                            fontFamily = InterLightFontFamily
                        )
                    }

                    Text(
                        modifier = Modifier
                            .padding(top = 11.dp, start = 4.dp, end = 4.dp)
                            .fillMaxHeight()
                            .align(Alignment.CenterVertically),
                        text = "-",
                        style = TextStyle(fontSize = 40.sp),
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = InterLightFontFamily
                    )

                    Column(
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        Text(
                            modifier = Modifier,
                            text = endCalendar.get(Calendar.DAY_OF_MONTH).toString(),
                            style = TextStyle(fontSize = 40.sp),
                            color = MaterialTheme.colorScheme.primary,
                            fontFamily = InterLightFontFamily
                        )
                        Text(
                            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                            text = monthArray[endCalendar.get(Calendar.MONTH)],
                            style = TextStyle(fontSize = 12.sp),
                            color = MaterialTheme.colorScheme.primary,
                            fontFamily = InterLightFontFamily
                        )
                    }
                }
            }
        }

        CategoriesList(categories = conference.categories)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                modifier = Modifier,
                contentDescription = "",
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_location)
            )
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = "${conference.city}, ${conference.country}",
                style = TextStyle(fontSize = 14.sp),
                color = MaterialTheme.colorScheme.primary,
                fontFamily = InterNormalFontFamily
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

    }
}

@Composable
@Preview(showBackground = true)
fun PreviewConferenceItem(modifier: Modifier = Modifier) {
    PartnerkinTestAppTheme {
        ItemConference(
            conference = ConferenceListItem.ConferenceItem(
                id = 3228,
                name = "SiGMA Africa 2025",
                format = "offline",
                status = "publish",
                statusTitle = "Опубликована",
                url = "sigma-africa-2025",
                image = com.example.partnerkintestapp.data.remote.dto.Image(
                    id = "f4bad773d6e5c03bc934706d88785bbf",
                    url = "https://partnerkin.com/uploads/webp/conferences/81e62a7f948c48175e7d5eaa.webp",
                    preview = "https://partnerkin.com/uploads/comp/webp/conferences/81e62a7f948c48175e7d5eaa.webp",
                    placeholderColor = null,
                    width = 480,
                    height = 320
                ),
                rating = "",
                startDate = "2025-03-10",
                endDate = "2025-03-12",
                oneDay = 0,
                customDate = "",
                countryId = 52,
                country = "ЮАР",
                cityId = 1274,
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
                2,
                type = Type(
                    2,
                    "Конференция"
                )
            )
        ) {
        }
    }
}
