package com.quraanali.trendingmovies.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.quraanali.trendingmovies.R
import com.quraanali.trendingmovies.ui.theme.dp8

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    onSearchIconClick: (String) -> Unit,
) {
    val searchValue = remember {
        mutableStateOf("")
    }

    val trailingIconView = @Composable {
        IconButton(
            onClick = {
                searchValue.value = ""
                onValueChange(searchValue.value)
            },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = ""
            )
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(dp8))
            .background(Color.Transparent),

        ) {
        TextField(
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = { onSearchIconClick(searchValue.value) }
            ),
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(dp8))
                .background(Color.Transparent),
            value = searchValue.value,
            colors = TextFieldDefaults.colors(
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            onValueChange = { text ->
                searchValue.value = text
                onValueChange(searchValue.value)
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = ""
                )
            },
            trailingIcon = trailingIconView,
            placeholder = { Text(text = "Search", color = Color.Gray) },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchTextFieldPreview() {
    SearchTextField(Modifier, {}) {}
}