package com.muratdayan.shop.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.shop.R
import com.muratdayan.ui.components.FastWordButtonComp
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme
import com.muratdayan.ui.theme.extendedColors

@Composable
fun ShopCardComp(
    modifier: Modifier = Modifier,
    shopImageIcon: Painter,
    @DrawableRes shopBuyIcon: Int?=null,
    shopAmount: Int?=null,
    giftAmount: Int,
    giftIcon: Painter?=null,
    borderColor: Color,
    buttonOnClick: ()->Unit,
    buttonText:String?=null
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimensions.paddingMedium),
        contentAlignment = Alignment.TopCenter
    ){

        Card (
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(6.dp,borderColor),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.scrim),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    painter = shopImageIcon,
                    contentDescription = "Shop Image",
                    modifier = Modifier
                        .size(100.dp),
                    tint = MaterialTheme.extendedColors.customBlue.colorContainer
                )

                if (buttonText!=null){
                    FastWordButtonComp(
                        modifier = Modifier
                            .width(100.dp),
                        text = buttonText,
                        containerColor = borderColor,
                        iconTint = Color.Unspecified,
                        onClick = {
                            buttonOnClick()
                        }
                    )
                }else{
                    FastWordButtonComp(
                        modifier = Modifier
                            .width(100.dp),
                        icon = shopBuyIcon,
                        containerColor = borderColor,
                        iconText = shopAmount.toString(),
                        iconTint = Color.Unspecified,
                        onClick = {
                            buttonOnClick()
                        }
                    )
                }
            }
        }

        Card (
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(2.dp,borderColor),
            modifier = Modifier
                .offset(y = (-16).dp)
                .height(40.dp)
                .wrapContentWidth()
        ){
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentWidth()
                    .background(MaterialTheme.colorScheme.scrim)
                    .padding(Dimensions.paddingSmall),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                FastWordTextComp(
                    text = "+$giftAmount",
                    fontWeight = FontWeight.Bold
                )

                giftIcon?.let {
                    Icon(
                        painter = giftIcon,
                        contentDescription = "Gift Icon",
                        tint = MaterialTheme.colorScheme.background,
                        modifier = Modifier
                            .size(15.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShopCardCompPreview() {
    FastWordTheme {
        ShopCardComp(
            shopBuyIcon = com.muratdayan.ui.R.drawable.ic_emerald,
            shopAmount = 10,
            shopImageIcon = painterResource(com.muratdayan.ui.R.drawable.ic_flash),
            giftAmount = 180,
            borderColor = MaterialTheme.extendedColors.customBlue.colorContainer,
            giftIcon = painterResource(com.muratdayan.ui.R.drawable.ic_flash),
            buttonOnClick = {}
        )
    }
}