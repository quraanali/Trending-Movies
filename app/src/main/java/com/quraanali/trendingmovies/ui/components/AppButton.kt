package com.quraanali.trendingmovies.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.quraanali.trendingmovies.ui.theme.PrimaryColor
import com.quraanali.trendingmovies.ui.theme.PrimaryColorDisabled
import com.quraanali.trendingmovies.ui.theme.dp10
import com.quraanali.trendingmovies.ui.theme.sp14

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    text: String = "",
    padding: Dp = dp10,
    textColor: Color = White,
    color: Color = PrimaryColor,
    fontSize: TextUnit = sp14,
    cornerShape: CornerBasedShape = RoundedCornerShape(dp10),
    fontFamily: FontFamilyEnum = FontFamilyEnum.REGULAR,
    isEnable: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick, modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = PrimaryColorDisabled
        ), shape = cornerShape,
        enabled = isEnable
    ) {
        Text(
            text, color = textColor, fontSize = fontSize,
            modifier = Modifier.padding(padding),
            fontFamily = fontFamily.fontFamily
        )
    }


}

@Preview
@Composable
fun AppButtonPreview() {
    AppButton(text = "alksdaffl sdfsd ", fontFamily = FontFamilyEnum.REGULAR) {}
}