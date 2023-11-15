package com.quraanali.trendingmovies.ui.components

import androidx.compose.ui.text.font.FontFamily

enum class FontFamilyEnum(val fontFamily: FontFamily) {
    REGULAR(
        FontFamily(
            androidx.compose.ui.text.font.Font(
                com.quraanali.trendingmovies.R.font.sf_regular
            )
        )
    ),

    MEDIUM(
        FontFamily(
            androidx.compose.ui.text.font.Font(
                com.quraanali.trendingmovies.R.font.sf_regular
            )
        )
    ),
    BOLD(
        FontFamily(
            androidx.compose.ui.text.font.Font(
                com.quraanali.trendingmovies.R.font.sf_bold
            )
        )
    )
}