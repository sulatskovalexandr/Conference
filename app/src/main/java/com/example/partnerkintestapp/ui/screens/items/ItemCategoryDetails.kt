package com.example.partnerkintestapp.ui.screens.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.partnerkintestapp.data.remote.dto.Category
import com.example.partnerkintestapp.ui.theme.InterSemiboldFontFamily


@Composable
fun ItemCategoryDetails(
    modifier: Modifier = Modifier, category: Category
) {
    Text(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = {})
            .background(
                MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(20.dp)
            )
            .padding(vertical = 4.dp, horizontal = 10.dp),
        text = category.name,
        style = TextStyle(fontSize = 12.sp),
        color = MaterialTheme.colorScheme.primary,
        fontFamily = InterSemiboldFontFamily
    )

}

@Composable
fun CategoriesDetailsList(modifier: Modifier = Modifier, categories: List<Category>) {
    LazyRow(
        modifier = modifier.padding(top = 24.dp), contentPadding = PaddingValues()
    ) {
        items(categories.size) { index ->
            ItemCategoryDetails(
                modifier = Modifier.padding(end = 8.dp), category = categories[index]
            )
        }
    }
}