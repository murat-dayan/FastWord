package com.muratdayan.game.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.game.R
import com.muratdayan.game.presentation.main.component.FriendCardComp
import com.muratdayan.ui.components.FastWordAdvertHeaderComp
import com.muratdayan.ui.components.FastWordBarHeaderComp
import com.muratdayan.ui.components.FastWordButtonComp
import com.muratdayan.ui.components.FastWordProfileImageComp
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme

@Composable
internal fun MainScreenRoot(
    modifier: Modifier= Modifier
){

    MainScreen(
        modifier = modifier
    )
}

@Composable
private fun MainScreen(
    modifier: Modifier = Modifier
){
    Column (
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(Dimensions.paddingSmall),
        verticalArrangement = Arrangement.spacedBy(Dimensions.spacingMedium)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){

            FastWordProfileImageComp(
                imagePainter = painterResource(com.muratdayan.ui.R.drawable.avatar),
                size = 50
            )

            FastWordBarHeaderComp(
                currentEnergy = 10,
                maxEnergy = 10,
                coinValue = 12,
                emeraldValue = 2
            )
        }
        FastWordAdvertHeaderComp()

        FastWordButtonComp(
            text = "Play Now",
            energyText = "3",
            onClick = {},
            icon = com.muratdayan.ui.R.drawable.ic_flash,
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            iconTint = MaterialTheme.colorScheme.primary
        )

        FastWordTextComp(
            text = "My Friends",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
            fontSize = Dimensions.textSizeLarge
        )

        LazyRow {
            items(10){
                FriendCardComp(
                    friendImagePainter = painterResource(com.muratdayan.ui.R.drawable.avatar),
                    friendName = "Murat"
                )
            }
        }

        FastWordTextComp(
            text = "My Invitations\n",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
            fontSize = Dimensions.textSizeLarge
        )


    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    FastWordTheme {
        MainScreen()
    }
}