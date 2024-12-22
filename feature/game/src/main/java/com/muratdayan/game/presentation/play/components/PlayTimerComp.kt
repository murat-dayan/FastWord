package com.muratdayan.game.presentation.play.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muratdayan.game.R
import com.muratdayan.ui.components.FastWordProfileImageComp
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme
import kotlinx.coroutines.delay

@Composable
fun PlayTimerComp(
    modifier: Modifier = Modifier,
    totalTime: Int = 10,
    imageUri: String,
    username: String
){

    var progress by remember { mutableFloatStateOf(1f) }

    LaunchedEffect(totalTime) {
        for (time in totalTime downTo 1){
            progress = time / totalTime.toFloat()
            delay(1000L)
        }
        progress = 0f
    }

    Row (
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ){
        FastWordProfileImageComp(
            imagePainter = painterResource(com.muratdayan.ui.R.drawable.avatar),
            size = 40
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            FastWordTextComp(
                text = "username",
                color = MaterialTheme.colorScheme.primary
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .background(
                        MaterialTheme.colorScheme.outline.copy(0.3f),
                        MaterialTheme.shapes.medium
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(progress.coerceIn(0f, 1f))
                        .background(
                            MaterialTheme.colorScheme.tertiary,
                            MaterialTheme.shapes.medium
                        )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PlayTimerCompPreview(){
    FastWordTheme {
        PlayTimerComp(
            totalTime = 10,
            imageUri = "",
            username = "username"
        )
    }
}