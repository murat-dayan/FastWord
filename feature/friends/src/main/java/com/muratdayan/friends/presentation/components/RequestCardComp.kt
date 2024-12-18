package com.muratdayan.friends.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.muratdayan.friends.R
import com.muratdayan.ui.components.FastWordBaseCardComp
import com.muratdayan.ui.components.FastWordProfileImageComp
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme

@Composable
fun RequestCardComp(
    modifier: Modifier = Modifier,
    imagePainter: Painter = painterResource(com.muratdayan.ui.R.drawable.avatar),
    onClickImage: () -> Unit = {},
    userName: String = "",
    onClickAccept: () -> Unit = {},
    onClickReject: () -> Unit = {}
) {
    FastWordBaseCardComp {
        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Dimensions.spacingMedium)
            ){
                FastWordProfileImageComp(
                    modifier = Modifier,
                    imagePainter = imagePainter,
                    size = 30,
                    onClick = onClickImage
                )

                FastWordTextComp(
                    text = userName,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }

            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Dimensions.spacingMedium)
            ){
                IconButton(
                    onClick = {
                        onClickAccept()
                    }
                ) {
                    Icon(
                        painter = painterResource(com.muratdayan.ui.R.drawable.ic_tick_yes),
                        contentDescription = "Yes",
                        tint = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier
                            .size(50.dp)
                    )
                }
                IconButton(
                    onClick = {
                        onClickReject()
                    }
                ) {
                    Icon(
                        painter = painterResource(com.muratdayan.ui.R.drawable.ic_close_no),
                        contentDescription = "No",
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .size(30.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun RequestCardCompPreview() {
    FastWordTheme {
        RequestCardComp(
            userName = "Murat"
        )
    }
}