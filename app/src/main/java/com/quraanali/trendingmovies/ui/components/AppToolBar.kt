package com.quraanali.trendingmovies.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.quraanali.trendingmovies.R
import com.quraanali.trendingmovies.ui.theme.Gray
import com.quraanali.trendingmovies.ui.theme.Transparent
import com.quraanali.trendingmovies.ui.theme.White
import com.quraanali.trendingmovies.ui.theme.sp20

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolBar(
    modifier: Modifier = Modifier,
    title: String = "",
    titleSize: TextUnit = sp20,
    onBackClicked: () -> Unit,
    onActionClicked: () -> Unit,

    ) {
    CenterAlignedTopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(0f to Gray, 1000f to Transparent)
            ),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            navigationIconContentColor = Color.Gray,
            titleContentColor = Color.Gray
        ),

        title = {
            Text(
                text = title,
                fontSize = titleSize,
                fontWeight = FontWeight.Medium
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                onBackClicked()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "",
                    tint = White
                )
            }
        },
        actions = {
            IconButton(onClick = {
                onActionClicked()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = "",
                    tint = White
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun AppToolBarPreview() {
    AppToolBar(onBackClicked = {},
        onActionClicked = {})
}