package com.muratdayan.profile.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
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
import com.muratdayan.ui.components.FastWordTextComp
import com.muratdayan.ui.theme.FastWordTheme

@Composable
fun ProfileStatsTitleComp(
    modifier: Modifier = Modifier,
    iconPainter: Painter,
    iconTint: Color = Color.Unspecified,
    statTitleText: String
){
    Row (
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Dimensions.spacingSmall)
    ){
        Icon(
            painter = iconPainter,
            contentDescription = "Stat Icon",
            tint = iconTint,
            modifier = Modifier
                .size(30.dp)
        )

        FastWordTextComp(
            text = statTitleText,
            fontWeight = FontWeight.Bold,
            fontSize = Dimensions.textSizeMedium
        )
    }
}

@Preview
@Composable
private fun ProfileStatsTitleCompPreview() {
    FastWordTheme {
        ProfileStatsTitleComp(
            iconPainter = painterResource(com.muratdayan.ui.R.drawable.ic_coin),
            statTitleText = "Coin"
        )
    }
}