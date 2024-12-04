package com.muratdayan.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.ui.R
import com.muratdayan.ui.theme.FastWordTheme

@Composable
fun FastWordAdvertHeaderComp(
    modifier: Modifier = Modifier,
    onClickEnergyAdvert: () -> Unit = {},
    onClickCoinAdvert: () -> Unit = {}
){

    Row (
        modifier = modifier
            .wrapContentWidth()
            .padding(Dimensions.paddingSmall),
        horizontalArrangement = Arrangement.spacedBy(Dimensions.spacingSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {

        FastWordAdvertButtonComp(
            modifier = Modifier,
            priceValue = 3,
            iconValue = R.drawable.ic_flash,
            onClick = onClickEnergyAdvert
        )
        FastWordAdvertButtonComp(
            modifier = Modifier,
            priceValue = 50,
            iconValue = R.drawable.ic_coin,
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            onClick = onClickCoinAdvert
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FastWordAdvertHeaderCompPreview(){
    FastWordTheme {
        FastWordAdvertHeaderComp()
    }
}