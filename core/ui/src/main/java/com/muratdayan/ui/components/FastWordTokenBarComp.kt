package com.muratdayan.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.ui.R
import com.muratdayan.ui.theme.FastWordTheme

@Composable
fun FastWordTokenBarComp(
    tokenValue: Int = 0,
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int = R.drawable.ic_emerald,
){

    Box(
        modifier = modifier
            .height(50.dp)
            .width(80.dp),
        contentAlignment = Alignment.CenterStart
    ) {

        Box(
            modifier = modifier
                .height(30.dp)
                .wrapContentWidth()
                .clip(MaterialTheme.shapes.small)
                .align(Alignment.CenterEnd)
                .background(MaterialTheme.colorScheme.scrim)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.scrim)

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
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight(),
                    tint = Color.Unspecified
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
            text = tokenValue.toString(),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = Dimensions.paddingSmall),
            color = MaterialTheme.colorScheme.background,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}




@Preview
@Composable
private fun FastWordTokenBarCompPreview(){
    FastWordTheme {
        FastWordTokenBarComp()
    }

}