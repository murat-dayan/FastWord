package com.muratdayan.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.ui.theme.FastWordTheme

@Composable
fun FastWordGameCardComp(
    modifier: Modifier = Modifier,
    imagePainter: Painter = painterResource(com.muratdayan.ui.R.drawable.avatar),
    name: String,
    cardInfo: String,
    score: String?=null
){

    FastWordBaseCardComp {
        Row (
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Dimensions.spacingMedium)
        ){
            FastWordProfileImageComp(
                modifier = Modifier,
                imagePainter = imagePainter,
                size = 30
            )
            Column (
                modifier = Modifier
                    .weight(1f)
            ){
                FastWordTextComp(
                    text = name,
                    color = MaterialTheme.colorScheme.scrim,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimensions.textSizeMedium
                )

                FastWordTextComp(
                    text = cardInfo,
                    color = MaterialTheme.colorScheme.scrim,
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = Dimensions.textSizeSmall
                )
            }
            score?.let {
                Box(
                    modifier = Modifier
                        .weight(0.80f)
                        .background(MaterialTheme.colorScheme.error, MaterialTheme.shapes.small),
                ){
                    FastWordTextComp(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = score,
                        color = MaterialTheme.colorScheme.background,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            FastWordButtonComp(
                modifier = Modifier
                    .weight(1.5f),
                text = "Play",
                onClick = {},
                icon = com.muratdayan.ui.R.drawable.ic_flash,
                iconTint = MaterialTheme.colorScheme.primary)
        }
    }
}

@Preview
@Composable
private fun FastWordGameCardCompPreview(){
    FastWordTheme {
        FastWordGameCardComp(
            name = "Murat",
            cardInfo = "Test",
            score = "1-0"
        )
    }
}