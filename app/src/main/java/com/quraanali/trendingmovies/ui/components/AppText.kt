package com.quraanali.trendingmovies.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import com.quraanali.trendingmovies.ui.theme.TextPrimary
import com.quraanali.trendingmovies.ui.theme.sp14

@Composable
fun AppText(
    modifier: Modifier = Modifier,
    text: String = "",
    textColor: Color = TextPrimary,
    textSize: TextUnit = sp14,
    fontFamily: FontFamilyEnum = FontFamilyEnum.REGULAR,
    textAlign: TextAlign = TextAlign.Start,
    maxLine: Int = 20,
    style: TextStyle? = null
) {
    Text(
        text = text,
        fontSize = textSize,
        fontWeight = FontWeight.Medium,
        modifier = modifier,
        color = textColor,
        fontFamily = fontFamily.fontFamily,
        textAlign = textAlign,
        style = style
            ?: TextStyle(
                lineHeight = 1.1.em, platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                )
            ),
        maxLines = maxLine
    )
}