package com.muratdayan.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ){
        Row (
            modifier = Modifier
                .wrapContentSize()
                .padding(Dimensions.paddingSmall),
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
            Spacer(modifier = Modifier.height(Dimensions.spacingMedium))
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
                    contentDescription = null,
                    tint = Color.Unspecified
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