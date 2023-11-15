package com.quraanali.trendingmovies.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TextTitleValue(
    pair: Pair<String, String>, isLink: Boolean = false, onClick: (String) -> Unit = {}
) {
    Row(modifier = Modifier.wrapContentSize()) {

        AppText(
            text = pair.first + ": ", fontFamily = FontFamilyEnum.BOLD
        )

        if (isLink) {
            AppText(
                text = pair.second,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                modifier = Modifier.clickable {
                    onClick(pair.second)
                },
                textColor = Color.Blue
            )
        } else {
            AppText(
                text = pair.second
            )
        }


    }
}

@Preview
@Composable
fun Pre() {
    TextTitleValue(pair = Pair("aaaa", "aqwe"), true)
}