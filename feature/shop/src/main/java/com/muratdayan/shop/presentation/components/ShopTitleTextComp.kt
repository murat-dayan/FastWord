package com.muratdayan.shop.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
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
import com.muratdayan.ui.R
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme
import com.muratdayan.ui.theme.extendedColors

@Composable
fun ShopTitleTextComp(
    modifier: Modifier = Modifier,
    title: String = "",
    iconPainter: Painter,
    iconTint:Color,
){

    Row (
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.scrim.copy(alpha = 0.9f))
            .padding(Dimensions.paddingXxSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Dimensions.spacingSmall,Alignment.Start)
    ){

        Icon(
            painter = iconPainter,
            modifier = Modifier
                .size(15.dp),
            contentDescription = "Title Icon",
            tint = iconTint
        )
        FastWordTextComp(
            text = title,
            fontSize = Dimensions.textSizeSmall,
            fontWeight = FontWeight.Bold
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun ShopTitleTextCompPreview() {
    FastWordTheme {
        ShopTitleTextComp(
            title = "Energy",
            iconPainter = painterResource(R.drawable.ic_flash),
            iconTint = MaterialTheme.extendedColors.customBlue.colorContainer
        )
    }
}