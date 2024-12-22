package com.muratdayan.game.presentation.play.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.game.R
import com.muratdayan.ui.components.FastWordBaseCardComp
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme

@Composable
fun PlayBoostHelperComp(
    modifier: Modifier = Modifier,
    boostName: String,
    boostIcon: Painter,
    boostAmount: Int,
    onClick: () -> Unit
){
    FastWordBaseCardComp (
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.scrim.copy(0.8f),
        height = 50
    ){
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimensions.paddingSmall)
        ){
            FastWordBaseCardComp (
                modifier = Modifier
                    .weight(1f),
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                onClick = {
                    onClick()
                }
            ){
                Row {
                    FastWordTextComp(
                        text = boostName,
                        color = MaterialTheme.colorScheme.scrim
                    )
                    Icon(
                        painter = boostIcon,
                        contentDescription = "Boost Icon",
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
            }

            Row (
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                FastWordTextComp(
                    text = boostAmount.toString(),
                )

                Icon(
                    painter = painterResource(com.muratdayan.ui.R.drawable.ic_coin),
                    contentDescription = "Boost Amount Icon",
                    modifier = Modifier
                        .size(20.dp),
                    tint = Color.Unspecified
                )

            }
        }
    }
}


@Preview
@Composable
private fun PlayBoostHelperCompPreview(){
    FastWordTheme {
        PlayBoostHelperComp(
            boostName = "Joker",
            boostIcon = painterResource(com.muratdayan.ui.R.drawable.ic_tick_yes),
            boostAmount = 60,
            onClick = {}
        )
    }
}