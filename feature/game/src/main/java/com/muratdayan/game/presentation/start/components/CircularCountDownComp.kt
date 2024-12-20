package com.muratdayan.game.presentation.start.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme
import com.muratdayan.ui.theme.extendedColors

@Composable
fun CircularCountDownComp(
    currentValue: Int,
    maxValue: Int,
    modifier: Modifier = Modifier,
    borderWidth: Dp = 8.dp,
    circleColor: Color = MaterialTheme.extendedColors.customBlue.colorContainer,
    textColor: Color = MaterialTheme.colorScheme.background
){
    val progress = currentValue.toFloat() / maxValue.toFloat()

    Box(
        modifier = modifier
            .size(100.dp),
        contentAlignment = Alignment.Center
    ){
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val size = size.minDimension
            val strokeWidth = borderWidth.toPx()

            drawArc(
                color = circleColor,
                startAngle = -90f,
                sweepAngle =  360f * progress,
                useCenter = false,
                style = Stroke(strokeWidth, cap = StrokeCap.Round),
                size = androidx.compose.ui.geometry.Size(size,size),
                topLeft = Offset(
                    x = (this.size.width - size)/2,
                    y = (this.size.height - size)/2
                )
            )
        }

        FastWordTextComp(
            text = currentValue.toString(),
            color = textColor,
            fontWeight = FontWeight.Bold,
            fontSize = Dimensions.textSizeXLarge
        )
    }
}

@Preview
@Composable
private fun CircularCountDownCompPreview(){
    FastWordTheme {
        CircularCountDownComp(
            currentValue = 1,
            maxValue = 10

        )
    }
}