package com.muratdayan.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.ui.theme.FastWordTheme

@Composable
fun FastWordTextComp(
    text:String,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = MaterialTheme.colorScheme.background,
    modifier: Modifier = Modifier,
    maxLines:Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = Dimensions.textSizeMedium,
    lineHeight: TextUnit = Dimensions.lineHeightXLarge,
    onClick: ()->Unit = {}
){
    Text(
        modifier = modifier
            .clickable {
                onClick()
            },
        text = text,
        style = style,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        fontWeight = fontWeight,
        fontSize = fontSize,
        lineHeight = lineHeight,

        )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MoodityTextPreview(){
    FastWordTheme {
        FastWordTextComp(
            text = "PreviewPreviewPreviewPreviewPreviewPreviewPreviewPreview",
            color = Color.Red
        )
    }
}