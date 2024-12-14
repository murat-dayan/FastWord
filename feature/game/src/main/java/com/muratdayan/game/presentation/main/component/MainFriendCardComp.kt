package com.muratdayan.game.presentation.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.game.R
import com.muratdayan.ui.components.FastWordButtonComp
import com.muratdayan.ui.components.FastWordProfileImageComp
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme

@Composable
internal fun FriendCardComp(
    modifier: Modifier= Modifier,
    friendImagePainter: Painter,
    friendName: String
){

    Card (
        modifier = modifier
            .width(100.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimensions.paddingSmall),
            verticalArrangement = Arrangement.spacedBy(Dimensions.spacingMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            FastWordProfileImageComp(
                imagePainter = friendImagePainter,
                size = 60
            )

            FastWordTextComp(
                text = friendName,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = Dimensions.textSizeMedium,
                fontWeight = FontWeight.Bold,
            )

            FastWordButtonComp(
                text = "Play",
                onClick = {},
                icon = com.muratdayan.ui.R.drawable.ic_flash,
                iconTint = MaterialTheme.colorScheme.primary,
                iconText = "3"
            )

        }
    }

}

@Preview
@Composable
private fun FriendCardCompPreview(){
    FastWordTheme {
        FriendCardComp(
            friendImagePainter = painterResource(com.muratdayan.ui.R.drawable.avatar),
            friendName = "Murat"
        )
    }
}