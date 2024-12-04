package com.muratdayan.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.muratdayan.ui.R
import com.muratdayan.ui.theme.FastWordTheme
import com.muratdayan.ui.theme.extendedColors

@Composable
fun FastWordEnergyBarComp(
    currentEnergy: Int = 0,
    maxEnergy: Int = 0,
    modifier: Modifier = Modifier
){

    val energyFraction = currentEnergy.toFloat() / maxEnergy.toFloat()
    val barColor = MaterialTheme.colorScheme.onTertiaryContainer

    Box(
        modifier = Modifier
            .height(50.dp),
        contentAlignment = Alignment.CenterStart
    ) {



        Box(
            modifier = modifier
                .height(30.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colorScheme.scrim)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(energyFraction)
                    .background(barColor)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxHeight(),
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_flash),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight(),
                    tint = MaterialTheme.colorScheme.background
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = null,
                    modifier = Modifier.height(20.dp),
                    tint = MaterialTheme.colorScheme.background
                )
            }
        }

        FastWordTextComp(
            text = "$currentEnergy/$maxEnergy",
            modifier = Modifier
                .align(Alignment.Center),
            color = MaterialTheme.colorScheme.background,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
private fun FastWordEnergyBarCompPreview(){
    FastWordTheme {
        FastWordEnergyBarComp(
            currentEnergy = 3,
            maxEnergy = 10
        )
    }
}