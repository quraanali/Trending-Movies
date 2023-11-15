package com.quraanali.trendingmovies.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.quraanali.trendingmovies.ui.theme.PrimaryColor
import com.quraanali.trendingmovies.ui.theme.dp10
import com.quraanali.trendingmovies.ui.theme.sp14


@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    colors: TextFieldColors? = null,
    fontSize: TextUnit = sp14,
    fontFamily: FontFamilyEnum = FontFamilyEnum.REGULAR,
    onValueChange: (String) -> Unit,
) {
    val textValue = remember {
        mutableStateOf(text)
    }
    OutlinedTextField(
        value = textValue.value,
        modifier = modifier,
        textStyle = TextStyle(
            color = PrimaryColor,
            fontSize = fontSize,
            fontFamily = fontFamily.fontFamily
        ),
        singleLine = true,
        onValueChange = {
            textValue.value = it
            onValueChange.invoke(it)
        },
        colors = colors
            ?: OutlinedTextFieldDefaults.colors(
                disabledBorderColor = Color.Gray,
                focusedBorderColor = Color.Gray,
            ),
        shape = RoundedCornerShape(dp10),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Preview
@Composable
fun AppTextFieldPreview() {
    AppTextField(text = "Hiiiii") {

    }
}