package com.example.partnerkintestapp.ui.screens.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.partnerkintestapp.R
import com.example.partnerkintestapp.data.model.RelatedConference
import com.example.partnerkintestapp.ui.theme.ButtonBg

@Composable
fun ItemRelatedEvent(
    relatedConference: RelatedConference,
    modifier: Modifier = Modifier,
    onClick: (RelatedConference) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.background,
        onClick = { onClick(relatedConference) }
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (relatedConference.isNew) {
                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "New",
                        color = ButtonBg,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))
            }

            Text(
                text = relatedConference.title,
                color = if (relatedConference.isNew) {
                    ButtonBg
                } else {
                    MaterialTheme.colorScheme.primary
                },
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
            )

            if (relatedConference.rating != null) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = "Rating",
                    tint = Color(0xFFFFB800),
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = (relatedConference.rating ?: "").toString(),
                color = MaterialTheme.colorScheme.primary,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = "Next",
                tint = if (relatedConference.isNew) {
                    ButtonBg
                } else {
                    MaterialTheme.colorScheme.primary
                },
                modifier = Modifier.size(16.dp)
            )
        }
    }
}