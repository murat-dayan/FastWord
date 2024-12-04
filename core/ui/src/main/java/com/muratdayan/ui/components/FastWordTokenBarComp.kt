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
            .height(50.dp),
        contentAlignment = Alignment.CenterStart
    ) {

        Box(
            modifier = Modifier
                .height(30.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colorScheme.scrim)
        ) {

        }

        Box(
            modifier = Modifier
                .fillMaxHeight(),
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .padding(bottom = Dimensions.paddingSmall, end = Dimensions.paddingSmall)
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
                    modifier = Modifier
                        .size(width = 30.dp, height = 25.dp),
                    tint = MaterialTheme.colorScheme.background
                )
            }
        }

        FastWordTextComp(
            text = tokenValue.toString(),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(start = Dimensions.paddingMedium),
            color = MaterialTheme.colorScheme.background,
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