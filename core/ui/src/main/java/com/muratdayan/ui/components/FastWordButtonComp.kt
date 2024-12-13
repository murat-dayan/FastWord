package com.muratdayan.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.muratdayan.core_ui.ui.theme.Dimensions
import com.muratdayan.ui.R
import com.muratdayan.ui.theme.FastWordTheme
import com.muratdayan.ui.theme.extendedColors

@Composable
fun FastWordButtonComp(
    text: String?= null,
    iconText: String? = null,
    energyText: String = "3",
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int? = null,
    iconTint: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.primary,
    textSize: TextUnit = Dimensions.textSizeMedium,
    textAlignment: TextAlign = TextAlign.Start,
    containerColor: Color = MaterialTheme.extendedColors.customBlue.colorContainer
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(containerColor)
            .clickable { onClick() }
            .padding(Dimensions.paddingSmall),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimensions.paddingMedium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            text?.let {
                FastWordTextComp(
                    text = text,
                    modifier = Modifier
                        .weight(1f),
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = textSize,
                    textAlign = textAlignment
                )
            }

            icon?.let {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    iconText?.let {
                        FastWordTextComp(
                            text = energyText,
                            modifier = Modifier,
                            color = textColor,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontSize = Dimensions.textSizeLarge
                        )
                    }

                    Icon(
                        painter = painterResource(icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                        ,
                        tint = iconTint,
                    )

                }
            }
        }
    }
}

@Preview
@Composable
private fun FastWordButtonCompPreview(){
    FastWordTheme {
        FastWordButtonComp(
            text = "asdsd",
            icon = null,
            textAlignment = TextAlign.Center
        )
    }
}