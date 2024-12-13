package com.muratdayan.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.ui.R
import com.muratdayan.ui.theme.FastWordTheme

@Composable
fun FastWordBarHeaderComp(
    currentEnergy: Int,
    maxEnergy: Int=10,
    modifier: Modifier = Modifier,
    coinValue: Int,
    emeraldValue: Int,
    onEnergyClick: ()->Unit={},
    onCoinClick: ()->Unit={},
    onEmeraldClick: ()->Unit={}
){

    Row (
        modifier = modifier
            .wrapContentWidth()
            .padding(Dimensions.paddingSmall),
        horizontalArrangement = Arrangement.spacedBy(Dimensions.spacingMedium,Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ){
        FastWordEnergyBarComp(
            currentEnergy = currentEnergy,
            maxEnergy = maxEnergy,
            onClick = onEnergyClick
        )
        FastWordTokenBarComp(
            tokenValue = coinValue,
            icon = R.drawable.ic_coin,
            onClick = onCoinClick
        )
        FastWordTokenBarComp(
            tokenValue = emeraldValue,
            icon = R.drawable.ic_emerald,
            onClick = onEmeraldClick
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun FastWordBarHeaderCompPreview(){
    FastWordTheme {
        FastWordBarHeaderComp(
            currentEnergy = 1,
            coinValue = 12,
            emeraldValue = 2
        )
    }
}