package com.muratdayan.profile.presentation.profile.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme

@Composable
fun CircularProgressComp(
    modifier: Modifier = Modifier,
    percentage: Float,
    size: Dp = 100.dp,
    strokeWidth: Dp = 12.dp
) {
    Box(
        modifier = modifier
            .size(size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val yellowSweepAngle = 360 * (percentage / 100f)
            val redSweepAngle = 360 - yellowSweepAngle

            val yellowStrokeWidth = strokeWidth +2.dp

            val canvasSize = size.toPx()
            val radius = (canvasSize - strokeWidth.toPx()) / 2
            val center = size.toPx() / 2

            val redRadius = (canvasSize - strokeWidth.toPx()) / 2

            drawCircle(
                color = Color.White,
                radius = (redRadius - (strokeWidth.toPx() / 3))
            )

            drawArc(
                color = Color.Red,
                startAngle = -90f + yellowSweepAngle,
                sweepAngle = redSweepAngle,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(),cap = StrokeCap.Round),
                topLeft = Offset(strokeWidth.toPx()/2, strokeWidth.toPx()/2),
                size = Size(radius*2, radius*2)
            )

            drawArc(
                color = Color.Yellow,
                startAngle = -90f,
                sweepAngle = yellowSweepAngle,
                useCenter = false,
                style = Stroke(width = yellowStrokeWidth.toPx(), cap = StrokeCap.Round),
                topLeft = Offset(yellowStrokeWidth.toPx() / 2, yellowStrokeWidth.toPx() / 2),
                size = Size(radius*2, radius*2)
            )


        }

        FastWordTextComp(
            modifier = Modifier,
            text = "${percentage.toInt()}%",
            color = MaterialTheme.colorScheme.primary,
            fontSize = Dimensions.textSizeLarge
        )
    }
}

@Preview
@Composable
private fun CircularProgressCompPreview() {
    FastWordTheme {
        CircularProgressComp(
            percentage = 80f
        )
    }
}