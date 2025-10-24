package com.example.partnerkintestapp.ui.screens.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.partnerkintestapp.R
import com.example.partnerkintestapp.common.parseDate
import com.example.partnerkintestapp.data.paging.ConferenceListItem
import java.util.Calendar

@Composable
fun ItemMonth(
    monthItem: ConferenceListItem.MonthItem
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        val startCalendar = parseDate(monthItem.startDate)
        val monthArray = stringArrayResource(R.array.monthsRu)
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "${monthArray[startCalendar.get(Calendar.MONTH)]}, ${
                startCalendar.get(Calendar.YEAR)
            } ",
            color = MaterialTheme.colorScheme.primary,
            style = TextStyle(fontSize = 18.sp)
        )
    }
}

@Preview()
@Composable
private fun PreviewMonthItem() {
    ItemMonth(ConferenceListItem.MonthItem("adadsa"))
}