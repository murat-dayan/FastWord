package com.muratdayan.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.ui.R
import com.muratdayan.ui.theme.FastWordTheme
import com.muratdayan.ui.theme.extendedColors

@Composable
fun FastWordAdvertButtonComp(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.extendedColors.customBlue.colorContainer,
    priceValue: Int,
    @DrawableRes iconValue:Int = R.drawable.ic_flash,
    onClick: () -> Unit = {}
){

    Box(
        modifier = modifier
            .height(50.dp)
            .wrapContentWidth()
            .background(containerColor)
            .clickable(
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center
    ){
        Row (
            modifier = Modifier
                .wrapContentSize()
                .padding(end = Dimensions.paddingSmall),
            horizontalArrangement = Arrangement.spacedBy(Dimensions.spacingSmall,Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = painterResource(R.drawable.ic_play_arrow),
                contentDescription = null,
            )
            FastWordTextComp(
                text = "AD",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.inverseSurface)
                    .padding(Dimensions.paddingSmall),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FastWordTextComp(
                    text = "+$priceValue",
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    painter = painterResource(iconValue),
                    modifier = Modifier
                        .size(30.dp),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
            }
        }
    }

}

@Preview
@Composable
private fun FastWordAdvertButtonCompPreview(){
    FastWordTheme {
        FastWordAdvertButtonComp(
            priceValue = 4
        )
    }
}