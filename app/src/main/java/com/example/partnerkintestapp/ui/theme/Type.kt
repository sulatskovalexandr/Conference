package com.example.partnerkintestapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.partnerkintestapp.R

val InterLightFontFamily = FontFamily(
    Font(R.font.inter_light, FontWeight.Light)
)
val InterRegularFontFamily = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal)
)
val InterNormalFontFamily = FontFamily(
    Font(R.font.inter_medium, FontWeight.Normal)
)

val InterSemiboldFontFamily = FontFamily(
    Font(R.font.inter_semi_bold, FontWeight.Normal)

)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = InterNormalFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

