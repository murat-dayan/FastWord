package com.muratdayan.shop.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.shop.R
import com.muratdayan.shop.presentation.components.ShopCardComp
import com.muratdayan.shop.presentation.components.ShopTitleTextComp
import com.muratdayan.ui.components.FastWordBarHeaderComp
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme
import com.muratdayan.ui.theme.extendedColors

@Composable
fun ShopScreenRoot(
    modifier: Modifier = Modifier,
){
    ShopScreen(
        modifier = modifier
    )
}

@Composable
private fun ShopScreen(
    modifier: Modifier = Modifier,
){
    Column (
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(Dimensions.paddingSmall),
        verticalArrangement = Arrangement.spacedBy(Dimensions.spacingMedium)
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(com.muratdayan.ui.R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.background
                )
            }

            Row {
                FastWordBarHeaderComp(
                    currentEnergy = 1,
                    maxEnergy = 10,
                    coinValue = 0,
                    emeraldValue = 0
                )
            }
        }

        LazyColumn {
            item {
                ShopTitleTextComp(
                    title = "Energy",
                    iconPainter = painterResource(com.muratdayan.ui.R.drawable.ic_flash),
                    iconTint = MaterialTheme.extendedColors.customBlue.colorContainer
                )

                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly

                ){
                    ShopCardComp(
                        modifier=Modifier.weight(1f),
                        shopBuyIcon = com.muratdayan.ui.R.drawable.ic_emerald,
                        shopAmount = 10,
                        shopImageIcon = painterResource(com.muratdayan.ui.R.drawable.ic_flash),
                        giftAmount = 10,
                        giftIcon = painterResource(com.muratdayan.ui.R.drawable.ic_flash),
                        borderColor = MaterialTheme.extendedColors.customBlue.colorContainer,
                        buttonOnClick = {}
                    )
                    ShopCardComp(
                        modifier=Modifier.weight(1f),
                        shopBuyIcon = com.muratdayan.ui.R.drawable.ic_emerald,
                        shopAmount = 10,
                        shopImageIcon = painterResource(com.muratdayan.ui.R.drawable.ic_flash),
                        giftAmount = 10,
                        giftIcon = painterResource(com.muratdayan.ui.R.drawable.ic_flash),
                        borderColor = MaterialTheme.extendedColors.customBlue.colorContainer,
                        buttonOnClick = {}
                    )

                }

                ShopTitleTextComp(
                    title = "Coin",
                    iconPainter = painterResource(com.muratdayan.ui.R.drawable.ic_coin),
                    iconTint = Color.Unspecified
                )

                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly

                ){
                    ShopCardComp(
                        modifier=Modifier.weight(1f),
                        shopBuyIcon = com.muratdayan.ui.R.drawable.ic_emerald,
                        shopAmount = 10,
                        shopImageIcon = painterResource(com.muratdayan.ui.R.drawable.ic_flash),
                        giftAmount = 10,
                        borderColor = MaterialTheme.colorScheme.secondaryContainer,
                        buttonOnClick = {}
                    )
                    ShopCardComp(
                        modifier=Modifier.weight(1f),
                        shopBuyIcon = com.muratdayan.ui.R.drawable.ic_emerald,
                        shopAmount = 10,
                        shopImageIcon = painterResource(com.muratdayan.ui.R.drawable.ic_flash),
                        giftAmount = 10,
                        borderColor = MaterialTheme.colorScheme.secondaryContainer,
                        buttonOnClick = {}
                    )

                }

                ShopTitleTextComp(
                    title = "Gem",
                    iconPainter = painterResource(com.muratdayan.ui.R.drawable.ic_emerald),
                    iconTint = Color.Unspecified
                )

                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly

                ){
                    ShopCardComp(
                        modifier=Modifier.weight(1f),
                        buttonText = "$99",
                        shopImageIcon = painterResource(com.muratdayan.ui.R.drawable.ic_flash),
                        giftAmount = 10,
                        borderColor = MaterialTheme.colorScheme.tertiaryContainer,
                        buttonOnClick = {}
                    )
                    ShopCardComp(
                        modifier=Modifier.weight(1f),
                        buttonText = "$99",
                        shopImageIcon = painterResource(com.muratdayan.ui.R.drawable.ic_flash),
                        giftAmount = 10,
                        borderColor = MaterialTheme.colorScheme.tertiaryContainer,
                        buttonOnClick = {}
                    )

                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShopScreenPreview() {
    FastWordTheme {
        ShopScreen()
    }
}